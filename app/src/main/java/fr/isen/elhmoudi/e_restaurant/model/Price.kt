package fr.isen.elhmoudi.e_restaurant.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Price(@SerializedName("price") val price: Float) : Serializable