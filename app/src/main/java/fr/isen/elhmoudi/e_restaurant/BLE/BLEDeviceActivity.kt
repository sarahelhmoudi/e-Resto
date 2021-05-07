package fr.isen.elhmoudi.e_restaurant.BLE

import android.bluetooth.*
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import fr.isen.elhmoudi.e_restaurant.R
import fr.isen.elhmoudi.e_restaurant.databinding.ActivityBLEDeviceBinding

class BLEDeviceActivity : AppCompatActivity() {


    private lateinit var binding: ActivityBLEDeviceBinding
    var bluetoothGatt: BluetoothGatt? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBLEDeviceBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val device = intent.getParcelableExtra<BluetoothDevice>("devices")
        binding.deviceName.text = device?.name ?: "Appareil inconnu"
        binding.deviceStatus.text = getString(R.string.ble_device_status_connecting)
        binding.deviceMac.text = device?.address

        connectToDevice(device)
    }

    private fun connectToDevice(device: BluetoothDevice?) {
        bluetoothGatt = device?.connectGatt(this, false, object : BluetoothGattCallback() {
            override fun onConnectionStateChange(gatt: BluetoothGatt, status: Int, newState: Int) {
                super.onConnectionStateChange(gatt, status, newState)
                connectionStateChange(newState, gatt)
            }

            override fun onServicesDiscovered(gatt: BluetoothGatt, status: Int) {
                super.onServicesDiscovered(gatt, status)
                runOnUiThread {
                    binding.serviceList.adapter = BLEDeviceAdapter(
                        gatt,
                        gatt?.services?.map {
                            BLEService(
                                it.uuid.toString(),
                                it.characteristics
                            )
                        }?.toMutableList() ?: arrayListOf(), this@BLEDeviceActivity)
                    binding.serviceList.layoutManager = LinearLayoutManager(this@BLEDeviceActivity)
                }
            }

            override fun onCharacteristicRead(
                gatt: BluetoothGatt,
                characteristic: BluetoothGattCharacteristic,
                status: Int
            ) {
                super.onCharacteristicRead(gatt, characteristic, status)
            }
        })
    }

    private fun connectionStateChange(newState: Int, gatt: BluetoothGatt) {
        BLEConnexionState.getBLEConnexionStateFromState(newState)?.let {
            runOnUiThread {
                binding.deviceStatus.text =
                    getString(R.string.ble_device_status, getString(it.text))
            }
            if (it.state == BLEConnexionState.STATE_C0NNECTED.state) {
                gatt?.discoverServices()
            }
        }
    }
}