package fr.isen.elhmoudi.e_restaurant.BLE

import android.bluetooth.le.ScanResult
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.isen.elhmoudi.e_restaurant.databinding.CellBLEBinding

class BLEScanAdapter(
    private val listDevices: MutableList<ScanResult>
) : RecyclerView.Adapter<BLEScanAdapter.DeviceViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DeviceViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CellBLEBinding.inflate(layoutInflater)
        return DeviceViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DeviceViewHolder, position: Int) {
        holder.address.text = listDevices[position].device.address
        holder.name.text = listDevices[position].device.name
    }

    fun addDevice(appareilData: ScanResult) {
        if (!listDevices.contains(appareilData))
            listDevices.add(appareilData)
    }

    override fun getItemCount(): Int = listDevices.size

    class DeviceViewHolder(binding: CellBLEBinding) : RecyclerView.ViewHolder(binding.root) {
        val name: TextView = binding.nameDevice
        val address: TextView = binding.macDevice
    }
}