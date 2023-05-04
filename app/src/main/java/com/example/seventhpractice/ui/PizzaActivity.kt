package com.example.seventhpractice.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.seventhpractice.PizzaOrder
import com.example.seventhpractice.R
import com.example.seventhpractice.databinding.ActivityPizzaBinding

class PizzaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPizzaBinding
    private lateinit var pizzaOrder: PizzaOrder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPizzaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        pizzaOrder = intent.getParcelableExtra("pizza_order")!!
        var flavor = ""

        binding.pizzaRadioGroup.setOnCheckedChangeListener { group, checkedId ->
            flavor = when (checkedId) {
                R.id.cheese -> getString(R.string.cheese)
                R.id.pepperoni -> getString(R.string.pepperoni)
                R.id.mushroom -> getString(R.string.mushroom)
                R.id.olive -> getString(R.string.olive)
                else -> ""
            }

            binding.flavorInfoTextView.text = flavor
            pizzaOrder.flavor = flavor
        }

        binding.confirmButton.setOnClickListener {
            Log.d("PizzaOrder", pizzaOrder.date)
            setResult(RESULT_OK, Intent().putExtra("pizza_order", pizzaOrder))
            finish()
        }
    }
}