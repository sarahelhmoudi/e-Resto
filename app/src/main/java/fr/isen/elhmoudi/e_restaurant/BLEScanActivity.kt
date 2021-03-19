package fr.isen.elhmoudi.e_restaurant

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import fr.isen.elhmoudi.e_restaurant.databinding.ActivityBLEScanBinding

class BLEScanActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBLEScanBinding
    private var isScanning = false
    private var bluetoothAdapter: BluetoothAdapter? = null

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bluetoothAdapter = getSystemService(BluetoothManager::class.java)?.adapter

        startBLEifPossible()


        binding = ActivityBLEScanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bleScanPlayPauseAction.setOnClickListener {
            togglePlayPauseAction()
        }


    }

    private fun startBLEifPossible() {
        when {
            !IsDeviceHasBLESupport() || bluetoothAdapter == null -> {
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
            else -> {
                //tout va bien on peut se connecter
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_ENABLE_BT && resultCode == RESULT_OK) {

        }
    }

    private fun IsDeviceHasBLESupport(): Boolean =
        packageManager.hasSystemFeature((PackageManager.FEATURE_BLUETOOTH_LE))


    private fun togglePlayPauseAction() {
        isScanning = !isScanning
        if (isScanning) {
            binding.bleScanTitle.text = getString(R.string.ble_scan_pause_title)
            binding.bleScanPlayPauseAction.setImageResource(R.drawable.ic_pause)
            binding.loadingProgress.isVisible = true
            binding.divider.isVisible = false
        } else {
            binding.bleScanTitle.text = getString(R.string.ble_scan_play_title)
            binding.bleScanPlayPauseAction.setImageResource(R.drawable.ic_play)
            binding.loadingProgress.visibility = View.INVISIBLE
            binding.divider.isVisible = true
        }
    }

    companion object {
        const private val REQUEST_ENABLE_BT = 33
    }
}