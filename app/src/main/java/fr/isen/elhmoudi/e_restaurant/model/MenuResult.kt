package fr.isen.elhmoudi.e_restaurant.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class MenuResult(@SerializedName("data") val categories: List<Category>) : Serializable
