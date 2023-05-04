package com.example.seventhpractice.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.seventhpractice.PizzaOrder
import com.example.seventhpractice.R
import com.example.seventhpractice.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var pizzaOrder: PizzaOrder

    private val dateActivityResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val returnedOrder = result.data?.getParcelableExtra<PizzaOrder>("pizza_order")
                pizzaOrder.date = returnedOrder?.date ?: ""
                binding.dateTextView.text = getString(R.string.date_info, pizzaOrder.date)
                Log.d("PizzaOrder", pizzaOrder.date)
            }
        }

    private val pizzaActivityResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val returnedOrder = result.data?.getParcelableExtra<PizzaOrder>("pizza_order")
                pizzaOrder.flavor = returnedOrder?.flavor ?: ""
                binding.pizzaTextView.text = getString(R.string.pizza_info, pizzaOrder.flavor)
            }
        }

    private val quantityActivityResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val returnedOrder = result.data?.getParcelableExtra<PizzaOrder>("pizza_order")
                pizzaOrder.quantity = returnedOrder?.quantity ?: 0
                if (pizzaOrder.quantity != 0) {
                    binding.quantityTextView.text =
                        getString(R.string.quantity_info, pizzaOrder.quantity)
                }
            }
        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set the content view using the binding object
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        pizzaOrder = PizzaOrder()
        Log.d("PizzaOrder", pizzaOrder.toString())


        binding.pickDateButton.setOnClickListener {
            val dateIntent = Intent(this, DateActivity::class.java)
            dateIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            dateIntent.putExtra("pizza_order", pizzaOrder)
            dateActivityResult.launch(dateIntent)
        }

        binding.choosePizzaButton.setOnClickListener {
            val pizzaIntent = Intent(this, PizzaActivity::class.java)
            pizzaIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            pizzaIntent.putExtra("pizza_order", pizzaOrder)
            pizzaActivityResult.launch(pizzaIntent)
        }

        binding.quantityButton.setOnClickListener {
            val quantityIntent = Intent(this, QuantityActivity::class.java)
            quantityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            quantityIntent.putExtra("pizza_order", pizzaOrder)
            quantityActivityResult.launch(quantityIntent)
        }
    }

    override fun onResume() {
        super.onResume()
        if (pizzaOrder.date.isNotEmpty() || pizzaOrder.flavor.isNotEmpty() || pizzaOrder.quantity != 0) {
            binding.oderInfoTextView.text = getString(R.string.order_info)
        }
    }
}