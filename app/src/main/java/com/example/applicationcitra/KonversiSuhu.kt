package com.example.applicationcitra

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class KonversiSuhu : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_konversi_suhu)

        val spinnerFrom = findViewById<Spinner>(R.id.spinner_from)
        val spinnerTo = findViewById<Spinner>(R.id.spinner_to)
        val inputEditText = findViewById<EditText>(R.id.edit_text_input)
        val resultTextView = findViewById<TextView>(R.id.text_result)
        val convertButton = findViewById<Button>(R.id.button_convert)

        convertButton.setOnClickListener {
            val nilaiString = inputEditText.text.toString().trim()

            if (nilaiString.isEmpty()) {
                Toast.makeText(this, "Masukkan angka terlebih dahulu", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            try {
                val angka = nilaiString.toDouble()
                val dari = spinnerFrom.selectedItem.toString()
                val ke = spinnerTo.selectedItem.toString()

                val hasil = konversiSuhu(angka, dari, ke)

                val formattedResult = String.format("%.2f", hasil)
                resultTextView.text = "$formattedResult $ke"

            } catch (e: NumberFormatException) {
                Toast.makeText(this, "Input tidak valid", Toast.LENGTH_SHORT).show()
            }
        }
    }

    /**
     * Melakukan konversi suhu.
     */
    private fun konversiSuhu(nilai: Double, dari: String, ke: String): Double {

        // Tahap 1 — Konversi semua ke Celsius
        val celsius = when (dari) {
            "Celsius" -> nilai
            "Fahrenheit" -> (nilai - 32.0) * 5.0 / 9.0
            "Kelvin" -> nilai - 273.15
            "Reamur" -> nilai * 5.0 / 4.0
            else -> nilai
        }

        // Tahap 2 — Konversi dari Celsius ke satuan tujuan
        return when (ke) {
            "Celsius" -> celsius
            "Fahrenheit" -> celsius * 9.0 / 5.0 + 32.0
            "Kelvin" -> celsius + 273.15
            "Reamur" -> celsius * 4.0 / 5.0
            else -> celsius
        }
    }
}
