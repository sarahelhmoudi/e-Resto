package fr.isen.elhmoudi.e_restaurant

import com.google.gson.annotations.SerializedName
import com.squareup.picasso.Picasso
import fr.isen.elhmoudi.e_restaurant.model.Ingredient
import fr.isen.elhmoudi.e_restaurant.model.Price
import java.io.Serializable

class Dish(
    @SerializedName("name_fr") val title: String,
    @SerializedName("images") val images: List<String>,
    @SerializedName("ingredients") val ingredients: List<Ingredient>,
    @SerializedName("prices") val prices: List<Price>
): Serializable  {
    fun getAffichagePrice() = prices[0].price
    fun getPrice() = prices[0].price

    fun getFirstPicture() = if (images.isNotEmpty() && images[0].isNotEmpty()) {
        images[0]
    } else {
        null
    }

    fun getIngredients(): String = ingredients.map(Ingredient::ing).joinToString(", ")
}