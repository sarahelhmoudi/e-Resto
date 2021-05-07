package fr.isen.elhmoudi.e_restaurant

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import fr.isen.elhmoudi.e_restaurant.BLE.BLEScanActivity
import fr.isen.elhmoudi.e_restaurant.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.mainAction.setOnClickListener {
            //client qui clique sur le bouton
            Toast.makeText(applicationContext, "Redirection vers les entrées", Toast.LENGTH_LONG)
                .show()
            //redirige vers une autre page
            val intent = Intent(this, CategoryActivity::class.java)
            intent.putExtra("category", "Entrées")
            startActivity(intent)
        }
        binding.mainAction1.setOnClickListener {
            //client qui clique sur le bouton
            Toast.makeText(applicationContext, "Redirection vers les plats", Toast.LENGTH_LONG)
                .show()
            //redirige vers une autre page
            val intent = Intent(this, CategoryActivity::class.java)
            intent.putExtra("category", "Plats")
            startActivity(intent)
        }
        binding.mainAction2.setOnClickListener {
            //client qui clique sur le bouton
            Toast.makeText(applicationContext, "Redirection vers les desserts", Toast.LENGTH_LONG)
                .show()
            Log.d("mon tag", "ndessert")
            //redirige vers une autre page
            val intent = Intent(this, CategoryActivity::class.java)
            intent.putExtra("category", "Desserts")
            startActivity(intent)
        }

        binding.ble.setOnClickListener {
            val intent = Intent(this, BLEScanActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("mon tag", "non de sort pas")
    }
}