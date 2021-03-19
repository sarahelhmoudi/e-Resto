package fr.isen.elhmoudi.e_restaurant

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.isen.elhmoudi.e_restaurant.databinding.MenuItemBinding


class CategoryAdapter(private val menu: List<String>,
                      private val clickListener: onItemClickListener) :
    RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    class CategoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.titleItem)
        val layout = view.findViewById<View>(R.id.titleItem)
    }

    override fun onCreateViewHolder (parent: ViewGroup,viewType: Int ): CategoryViewHolder {
        val binding = MenuItemBinding.inflate(LayoutInflater.from(parent.context),parent, false)

        return CategoryViewHolder(binding.root)
    }

    override fun getItemCount(): Int {
        return menu.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.title.text = menu[position]
        holder.layout.setOnClickListener{
            clickListener.onItemClick(menu[position])
        }
    }

    interface onItemClickListener {
        fun onItemClick(item : String)
    }
}


