package fr.isen.elhmoudi.e_restaurant.model

import com.google.gson.annotations.SerializedName
import fr.isen.elhmoudi.e_restaurant.Dish
import java.io.Serializable

class Category(
    @SerializedName("name_fr") val name: String,
    @SerializedName("items") val items: List<Dish>,
    @SerializedName("prices") private val prices : List<Price>,
    @SerializedName("images") private val images : List<String>
) : Serializable{
    fun getAffichagePrice() = prices[0].price
    fun getFirstPicture() = if(images.isNotEmpty() && images[0].isNotEmpty()) {
        images[0]
    }
    else
    {
        null
    }

}