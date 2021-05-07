package fr.isen.elhmoudi.e_restaurant.BLE

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.bluetooth.le.BluetoothLeScanner
import android.bluetooth.le.ScanCallback
import android.bluetooth.le.ScanResult
import android.bluetooth.le.ScanSettings
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import fr.isen.elhmoudi.e_restaurant.R
import fr.isen.elhmoudi.e_restaurant.databinding.ActivityBLEScanBinding


class BLEScanActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBLEScanBinding
    private var isScanning = false
    private var bluetoothAdapter: BluetoothAdapter? = null
    private var leDeviceListAdapter: BLEScanAdapter? = null
    private val handler = Handler()

    private var bluetoothLeScanner: BluetoothLeScanner? = bluetoothAdapter?.bluetoothLeScanner
    //private var scanning = false


    private val scanSettings = ScanSettings.Builder()
        .setScanMode(ScanSettings.SCAN_MODE_BALANCED)
        .build()

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBLEScanBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bluetoothAdapter = getSystemService(BluetoothManager::class.java)?.adapter

        initRecyclerDevice()
        startBLEIfPossible()
        isDeviceHasBLESupport()


        binding.bleScanPlayPauseAction.setOnClickListener {
            //bluetoothAdapter?.bluetoothLeScanner?.startScan(null, scanSettings, leScanCallback)
            togglePlayPauseAction()
            isDeviceHasBLESupport()
        }

        binding.bleScanTitle.setOnClickListener {
            togglePlayPauseAction()
        }

    }

    private fun startBLEIfPossible() {
        when {
            !isDeviceHasBLESupport() || bluetoothAdapter == null -> {
                Toast.makeText(
                    this,
                    "Cet apareil n'est pas compatible avec le BLE",
                    Toast.LENGTH_SHORT
                ).show()
            }
            !(bluetoothAdapter?.isEnabled ?: false) -> {
                //activer le bluetooth
                val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
                startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT)
            }
            ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED -> {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    REQUEST_PERMISSION_LOCATION
                )
            }
            else -> {
                //tout va bien on peut se connecter
                bluetoothLeScanner = bluetoothAdapter?.bluetoothLeScanner

            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_ENABLE_BT && resultCode == RESULT_OK) {
            startBLEIfPossible()
        }
    }

    private fun initRecyclerDevice() {
        leDeviceListAdapter = BLEScanAdapter(mutableListOf())
        binding.listAppareil.layoutManager = LinearLayoutManager(this)
        binding.listAppareil.adapter = leDeviceListAdapter
    }

    private fun isDeviceHasBLESupport(): Boolean {
        if (!packageManager.hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)) {
            Toast.makeText(this, "Cet appareil n'est pas compatible, sorry", Toast.LENGTH_SHORT)
                .show()
        }
        return packageManager.hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)
    }

    private fun scanLeDevice() {
        bluetoothLeScanner?.let { scanner ->
            if (isScanning) { // Stops scanning after a pre-defined scan period.
                handler.postDelayed({
                    isScanning = false
                    scanner.stopScan(leScanCallback)
                }, SCAN_PERIOD)
                isScanning = true
                scanner.startScan(null, scanSettings, leScanCallback)
            } else {
                isScanning = false
                scanner.stopScan(leScanCallback)
            }
        }
    }

    private fun togglePlayPauseAction() {
        isScanning = !isScanning
        if (isScanning) {
            Log.d("ScanDevices", "onRequestPermON")
            binding.bleScanTitle.text = getString(R.string.ble_scan_pause_title)
            binding.progressBar.isVisible = true
            binding.divider.isVisible = false
            binding.bleScanPlayPauseAction.setImageResource(R.drawable.ic_pause)
            scanLeDevice()
        } else {
            binding.bleScanTitle.text = getString(R.string.ble_scan_play_title)
            binding.loadingProgress.isVisible = false
            binding.divider.isVisible = true
            binding.bleScanPlayPauseAction.setImageResource(R.drawable.ic_play)
        }
    }

    private val leScanCallback: ScanCallback = object : ScanCallback() {
        override fun onScanResult(callbackType: Int, result: ScanResult) {
            super.onScanResult(callbackType, result)
            Log.d("BLEScan", "CC LE SCAN")
            leDeviceListAdapter?.addDevice(result)
            leDeviceListAdapter?.notifyDataSetChanged()
        }
    }


    companion object {
        const private val REQUEST_ENABLE_BT = 33
        const private val REQUEST_PERMISSION_LOCATION = 22

        // Stops scanning after 10 seconds.
        const private val SCAN_PERIOD: Long = 10000
    }

}