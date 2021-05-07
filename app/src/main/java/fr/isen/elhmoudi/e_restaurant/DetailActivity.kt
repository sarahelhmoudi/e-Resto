package fr.isen.elhmoudi.e_restaurant

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso

import fr.isen.elhmoudi.e_restaurant.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        val dataItem = intent.getSerializableExtra("choix") as? Dish
        setContentView(binding.root)


        if (dataItem != null) {
            binding.nameDetail.text = dataItem.title
            binding.priceDetail.text = dataItem.getAffichagePrice().toString()
            binding.ingredientDetail.text = dataItem.getIngredients()
            binding

            Picasso.get()
                .load(dataItem.getFirstPicture())
                .placeholder(R.drawable.no_photo)
                .into(binding.imageDetail)

        }


        var quantity = 0
        if (dataItem != null) {
            total(quantity, dataItem)
        }

        binding.plus.setOnClickListener {
            quantity++
            binding.quantite.text = quantity.toString()
            if (dataItem != null) {
                total(quantity, dataItem)
            }
        }

        binding.moins.setOnClickListener {
            if (quantity > 0)
                quantity--
            binding.quantite.text = quantity.toString()
            if (dataItem != null) {
                total(quantity, dataItem)
            }
        }

    }

    private fun total(quantity: Int, price: Dish) {
        val total = quantity * price.getPrice()
        binding.prixTotal.text = total.toString() + "€"
    }
}
    
