package com.example.calculations

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val spinner: Spinner = findViewById(R.id.spSelect)
        val number1: EditText = findViewById(R.id.number1)
        val number2: EditText = findViewById(R.id.number2)
        val calculateButton: Button = findViewById(R.id.calculateButton)
        val resultTextView: TextView = findViewById(R.id.resultTextView)

        // Set up the Spinner
        ArrayAdapter.createFromResource(
            this,
            R.array.operations_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }

        // Set up the Calculate Button
        calculateButton.setOnClickListener {
            val num1 = number1.text.toString().toDoubleOrNull()
            val num2 = number2.text.toString().toDoubleOrNull()
            if (num1 == null || num2 == null) {
                resultTextView.text = "Please enter valid numbers"
            } else {
                val result = when (spinner.selectedItem.toString()) {
                    "Addition" -> num1 + num2
                    "Subtraction" -> num1 - num2
                    "Multiplication" -> num1 * num2
                    "Division" -> {
                        if (num2 == 0.0) {
                            Toast.makeText(this, "Cannot divide by zero", Toast.LENGTH_SHORT).show()
                            return@setOnClickListener
                        } else {
                            num1 / num2
                        }
                    }
                    else -> 0.0
                }
                resultTextView.text = "Result: $result"
            }
        }
    }
}
