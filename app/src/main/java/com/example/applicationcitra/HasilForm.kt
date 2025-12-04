package com.example.applicationcitra

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class HasilForm : AppCompatActivity() {

    private lateinit var tvnama: TextView
    private lateinit var tvnomer: TextView
    private lateinit var tvalamat: TextView
    private lateinit var tvkelamin: TextView
    private lateinit var tvagama: TextView
    private lateinit var tvhobi: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_hasil_form)

        initViews()
        setData()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun initViews() {
        tvnama = findViewById(R.id.NamA)
        tvnomer = findViewById(R.id.Nomerr)
        tvalamat = findViewById(R.id.etAlamatt)
        tvkelamin = findViewById(R.id.tvKelamin)
        tvagama = findViewById(R.id.tvAgama)
        tvhobi = findViewById(R.id.tvHobi)
    }

    private fun setData() {
        tvnama.text = "Nama : ${intent.getStringExtra("Nama")}"
        tvnomer.text = "Nomor HP : ${intent.getStringExtra("Nomor")}"
        tvalamat.text = "Alamat : ${intent.getStringExtra("Alamat")}"
        tvkelamin.text = "Jenis Kelamin : ${intent.getStringExtra("Kelamin")}"
        tvagama.text = "Agama : ${intent.getStringExtra("Agama")}"
        tvhobi.text = "Hobi : ${intent.getStringExtra("Hobi")}"
    }
}
