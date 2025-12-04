package com.example.applicationcitra

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class CardActivity : AppCompatActivity() {

    private lateinit var cardForm: CardView
    private lateinit var cardProfil: CardView
    private lateinit var cardKalkulator: CardView
    private lateinit var cardFood: CardView
    private lateinit var cardTemperature: CardView
    private lateinit var cardKeluar: CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_card)

        init()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun init() {
        cardForm = findViewById(R.id.CardForm)
        cardProfil = findViewById(R.id.CardProfil)
        cardKalkulator = findViewById(R.id.CardCalkulator)
        cardFood = findViewById(R.id.CardFood)
        cardTemperature = findViewById(R.id.CardKonversi)
        cardKeluar = findViewById(R.id.CardExit)

        setCardListeners()
    }

    private fun setCardListeners() {
        cardForm.setOnClickListener {
            Toast.makeText(this, "Card View Form Diklik", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, RelatifActivity::class.java))
        }

        cardProfil.setOnClickListener {
            Toast.makeText(this, "Card View Profil Diklik", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, Profil::class.java))
        }

        cardKalkulator.setOnClickListener {
            Toast.makeText(this, "Card View Kalkulator Diklik", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, kalku::class.java))
        }

        cardFood.setOnClickListener {
            Toast.makeText(this, "Card View Food Diklik", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, cafe::class.java))
        }

        cardTemperature.setOnClickListener {
            Toast.makeText(this, "Card View Temperature Diklik", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, KonversiSuhu::class.java))
        }

        // ================================
        // Popup Konfirmasi Keluar Aplikasi
        // ================================
        cardKeluar.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Konfirmasi Keluar")
            builder.setMessage("Anda yakin ingin keluar dari aplikasi?")

            builder.setPositiveButton("Ya") { _, _ ->
                finishAffinity() // keluar total dari aplikasi
            }

            builder.setNegativeButton("Tidak") { dialog, _ ->
                dialog.dismiss()
            }

            builder.show()
        }
    }
}
