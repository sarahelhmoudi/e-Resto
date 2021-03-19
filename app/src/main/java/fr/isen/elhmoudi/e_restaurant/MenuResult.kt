package fr.isen.elhmoudi.e_restaurant

import com.google.gson.annotations.SerializedName


data class MenuResult(@SerializedName("data") val data: List<Category>)
