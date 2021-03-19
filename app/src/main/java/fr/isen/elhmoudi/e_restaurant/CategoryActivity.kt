package fr.isen.elhmoudi.e_restaurant

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import fr.isen.elhmoudi.e_restaurant.databinding.ActivityCategoryBinding
import org.json.JSONObject

class CategoryActivity : AppCompatActivity() , CategoryAdapter.onItemClickListener {

    private lateinit var binding: ActivityCategoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        
        val categoryName = intent.getStringExtra("category")
        binding.categoryTitle.text = categoryName



        loadData()

       // binding.menuList.layoutManager = LinearLayoutManager(this)
       // binding.menuList.adapter = CategoryAdapter(listOf("Pate", "Salade", "Voiture", "Burger","Pate", "Salade", "Voiture", "Burger"), this)

    }

    private fun loadData()
    {
        val url = "http://test.api.catering.bluecodegames.com/menu"

        val jsonInput = JSONObject()
        jsonInput.put("id_shop", "1")

        val jsonObjectRequest = JsonObjectRequest(Request.Method.POST, url, jsonInput,
            { response ->
                Log.d("CategoryActivity", "Response: %s".format(response.toString()))
                val menu  = Gson().fromJson(response.toString(), MenuResult::class.java)
                displayMenu(menu)
            },
            { error ->
                Log.e("CategoryActivity","Erreur: $error")
            }
        )


// Access the RequestQueue through your singleton class.
        Volley.newRequestQueue(this).add(jsonObjectRequest)
    }
    private fun displayMenu(menu: MenuResult) {
        val categoryTitleList = menu.data[1].dishes.map { it.title }
        binding.menuList.layoutManager = LinearLayoutManager(this)
        binding.menuList.adapter = CategoryAdapter(categoryTitleList, this)
    }

    override fun onItemClick(item: String) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("items", item)
        startActivity(intent)
    }
}