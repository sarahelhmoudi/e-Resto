package fr.isen.elhmoudi.e_restaurant

import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.isen.elhmoudi.e_restaurant.databinding.MenuItemBinding


class CategoryAdapter(private val menu: List<Dish>,
                      private val clickListener: onItemClickListener) :
    RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    inner class CategoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.categoryTitle)
        val layout = view.findViewById<View>(R.id.categoryTitle)
        val images: ImageView = view.findViewById(R.id.imageItem)
        val prices: TextView = view.findViewById(R.id.priceItem)


        fun bind(dish: Dish, position: Int) {
            title.text = menu[position].title
            prices.text = "${dish.prices.first().price}" + "€"

            Picasso.get()
                .load(dish.getFirstPicture())
                .placeholder(R.drawable.no_photo)
                .into(images)
        }
    }

    override fun onCreateViewHolder (parent: ViewGroup,viewType: Int ): CategoryViewHolder {
        val binding = MenuItemBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return CategoryViewHolder(binding.root)
    }

    override fun getItemCount(): Int {
        return menu.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val categoryName = menu[position]
        holder.title.text = menu[position].toString()

        holder.layout.setOnClickListener{
            clickListener.onItemClick(categoryName)
        }
        holder.bind(categoryName, position)
    }

    interface onItemClickListener {
        fun onItemClick(item: Dish)
    }
}


