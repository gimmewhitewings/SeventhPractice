package com.example.seventhpractice.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.seventhpractice.PizzaOrder
import com.example.seventhpractice.databinding.ActivityQuantityBinding

class QuantityActivity : AppCompatActivity() {

    private lateinit var binding: ActivityQuantityBinding
    private lateinit var pizzaOrder: PizzaOrder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuantityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        pizzaOrder = intent.getParcelableExtra("pizza_order")!!

        var quantity = 0
        binding.addButton.setOnClickListener {
            quantity++
            pizzaOrder.quantity = quantity
            binding.setQuantityTextView.text = quantity.toString()
        }

        binding.removeButton.setOnClickListener {
            if (quantity != 0) {
                quantity--
                pizzaOrder.quantity = quantity
                binding.setQuantityTextView.text = quantity.toString()
            }
        }

        binding.confirmButton.setOnClickListener {
            Log.d("PizzaOrder", pizzaOrder.date)
            setResult(RESULT_OK, Intent().putExtra("pizza_order", pizzaOrder))
            finish()
        }
    }
}