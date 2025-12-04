package com.example.applicationcitra

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.android.material.button.MaterialButton

class kalku : AppCompatActivity() {

    private lateinit var display: TextView
    private var current = ""
    private var operator = ""
    private var firstNumber = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kalku)

        display = findViewById(R.id.display)

        val buttons = listOf(
            R.id.btnC, R.id.btnDel, R.id.btnPercent, R.id.btnDivide,
            R.id.btn7, R.id.btn8, R.id.btn9, R.id.btnMultiply,
            R.id.btn4, R.id.btn5, R.id.btn6, R.id.btnMinus,
            R.id.btn1, R.id.btn2, R.id.btn3, R.id.btnPlus,
            R.id.btn0, R.id.btnDot, R.id.btnEqual
        )

        buttons.forEach { id ->
            findViewById<MaterialButton>(id)?.setOnClickListener {
                onButtonClick((it as Button).text.toString())
            }
        }
    }

    private fun onButtonClick(value: String) {
        when (value) {
            "C" -> {
                current = ""
                operator = ""
                firstNumber = ""
                display.text = "0"
            }

            "DEL" -> {
                current = if (current.isNotEmpty()) current.dropLast(1) else ""
                display.text = if (current.isEmpty()) "0" else current
            }

            "+", "−", "×", "÷", "%" -> {
                if (current.isNotEmpty()) {
                    firstNumber = current
                    operator = value
                    current = ""
                }
            }

            "=" -> calculate()

            else -> {
                current += value
                display.text = current
            }
        }
    }

    private fun calculate() {
        if (firstNumber.isEmpty() || current.isEmpty()) return

        val a = firstNumber.toDouble()
        val b = current.toDouble()
        var result = 0.0

        when (operator) {
            "+" -> result = a + b
            "−" -> result = a - b
            "×" -> result = a * b
            "÷" -> if (b != 0.0) result = a / b
            "%" -> result = a % b
        }

        display.text = if (result % 1 == 0.0) result.toInt().toString() else result.toString()

        current = display.text.toString()
        firstNumber = ""
        operator = ""
    }
}
