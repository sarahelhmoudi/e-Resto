package fr.isen.elhmoudi.e_restaurant.BLE

import android.bluetooth.BluetoothProfile
import androidx.annotation.StringRes
import fr.isen.elhmoudi.e_restaurant.R

enum class BLEConnexionState(val state: Int, @StringRes val text : Int) {

    STATE_C0NNECTING(BluetoothProfile.STATE_CONNECTING, R.string.ble_device_status_connecting),
    STATE_C0NNECTED(BluetoothProfile.STATE_CONNECTED, R.string.ble_device_status_connected),

    STATE_DISC0NNECTED(BluetoothProfile.STATE_DISCONNECTING, R.string.ble_device_status_disconnecting),
    STATE_DISC0NNECTING(BluetoothProfile.STATE_DISCONNECTED, R.string.ble_device_status_disconnected);

    companion object{
        fun getBLEConnexionStateFromState(state: Int) = values().firstOrNull{
            it.state == state
        }
    }
}