package fr.isen.elhmoudi.e_restaurant

import com.google.gson.annotations.SerializedName


class Category(
    @SerializedName("name_fr") val name: String,
    @SerializedName("items") val dishes: List<Dish>
) {
}