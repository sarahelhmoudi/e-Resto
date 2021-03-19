package fr.isen.elhmoudi.e_restaurant

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import fr.isen.elhmoudi.e_restaurant.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mainAction = findViewById<Button>(R.id.mainAction)
        binding.mainAction.setOnClickListener {
            //client qui clique sur le bouton
            //Toast.makeText( applicationContext, "Redirection vers les entrées", Toast.LENGTH_LONG).show()
            //redirige vers une autre page
            val intent = Intent(this@HomeActivity, CategoryActivity::class.java)
            intent.putExtra("category", "Entrées")
            startActivity(intent)
        }
        binding.mainAction1.setOnClickListener {
            //client qui clique sur le bouton
            //Toast.makeText( applicationContext, "Redirection vers les plats", Toast.LENGTH_LONG).show()
            //redirige vers une autre page
            val intent = Intent(this@HomeActivity, CategoryActivity::class.java)
            intent.putExtra("category", "Plats")
            startActivity(intent)
        }
        binding.mainAction2.setOnClickListener {
            //client qui clique sur le bouton
            //Toast.makeText(applicationContext, "Redirection vers les desserts", Toast.LENGTH_LONG).show()
            //redirige vers une autre page
            val intent = Intent(this@HomeActivity, CategoryActivity::class.java)
            intent.putExtra("category", "Plats")
            startActivity(intent)
        }

        binding.ble.setOnClickListener {
            val intent = Intent(this@HomeActivity, BLEScanActivity::class.java)
            startActivity(intent)
        }
    }
}