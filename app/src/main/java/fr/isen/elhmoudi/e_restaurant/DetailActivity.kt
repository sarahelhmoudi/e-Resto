package fr.isen.elhmoudi.e_restaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fr.isen.elhmoudi.e_restaurant.databinding.ActivityDetailBinding
import fr.isen.elhmoudi.e_restaurant.databinding.ActivityHomeBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}