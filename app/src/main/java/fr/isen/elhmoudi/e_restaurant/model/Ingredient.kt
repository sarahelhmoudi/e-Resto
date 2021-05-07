package fr.isen.elhmoudi.e_restaurant.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable


class Ingredient(@SerializedName("name_fr") val ing : String): Serializable