package com.example.seventhpractice.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.seventhpractice.PizzaOrder
import com.example.seventhpractice.R
import com.example.seventhpractice.databinding.ActivityDateBinding
import com.google.android.material.datepicker.MaterialDatePicker

class DateActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDateBinding
    private lateinit var pizzaOrder: PizzaOrder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        pizzaOrder = intent.getParcelableExtra("pizza_order")!!

        Log.d("PizzaOrder", pizzaOrder.toString())

        if (pizzaOrder.date.isEmpty()) {
            binding.chosenDateTextView.text = getString(R.string.no_date)
        } else {
            binding.chosenDateTextView.text = pizzaOrder.date
        }

        binding.openCalendarButton.setOnClickListener {
            val datePicker = MaterialDatePicker.Builder.datePicker()
                .setTitleText(getString(R.string.select_date))
                .setSelection(MaterialDatePicker.thisMonthInUtcMilliseconds()).build()

            datePicker.addOnCancelListener {
                Log.d("DatePicker", "cancel")
            }

            datePicker.addOnPositiveButtonClickListener {
                Log.d(
                    "DatePicker",
                    "Date String = ${datePicker.headerText}:: Date epoch value = $it"
                )
                pizzaOrder.date = datePicker.headerText
                Log.d("PizzaOrder", pizzaOrder.date)
                binding.chosenDateTextView.text = datePicker.headerText
            }

            datePicker.show(supportFragmentManager, datePicker.toString())
        }

        binding.confirmButton.setOnClickListener {
            Log.d("PizzaOrder", pizzaOrder.date)
            setResult(RESULT_OK, Intent().putExtra("pizza_order", pizzaOrder))
            finish()
        }
    }
}