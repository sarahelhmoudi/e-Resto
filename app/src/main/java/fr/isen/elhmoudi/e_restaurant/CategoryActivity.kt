package fr.isen.elhmoudi.e_restaurant

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import fr.isen.elhmoudi.e_restaurant.databinding.ActivityCategoryBinding
import fr.isen.elhmoudi.e_restaurant.model.MenuResult
import org.json.JSONException
import org.json.JSONObject

class CategoryActivity : AppCompatActivity(), CategoryAdapter.onItemClickListener {

    private var requestQueue: RequestQueue? = null
    private lateinit var binding: ActivityCategoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        requestQueue = Volley.newRequestQueue(this)

        val categoryName = intent.getStringExtra("category")?:""
        binding.categoryTitle.text = categoryName

        loadData(categoryName)
    }

    private fun loadData(categoryName: String) {
        val url = "http://test.api.catering.bluecodegames.com/menu"

        val jsonInput = JSONObject()
        jsonInput.put("id_shop", "1")

        val jsonObjectRequest =
            JsonObjectRequest(Request.Method.POST, url, jsonInput, { response ->
                try {
                    Log.d("CategoryActivity", "Response: %s".format(response.toString()))
                    val menu = Gson().fromJson(response.toString(), MenuResult::class.java)
                    displayMenu(menu, categoryName)
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }, { error ->
                error.printStackTrace()
            })
        requestQueue?.add(jsonObjectRequest)
    }

    private fun displayMenu(menu: MenuResult, categoryTitle: String){
        val dishes = menu.categories.firstOrNull{ it.name == categoryTitle }?.items ?: listOf()
        binding.menuList.layoutManager = LinearLayoutManager(this)
        binding.menuList.adapter = CategoryAdapter(dishes,this)
    }

    override fun onItemClick(item: Dish) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("choix", item)
        startActivity(intent)
    }
}