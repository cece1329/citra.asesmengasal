package com.example.applicationcitra

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class RelatifActivity : AppCompatActivity() {

    private lateinit var etNama: EditText
    private lateinit var etNomor: EditText
    private lateinit var etAlamat: EditText
    private lateinit var btnSimpan: Button
    private lateinit var rgKelamin: RadioGroup
    private lateinit var spAgama: Spinner
    private lateinit var cbMembaca: CheckBox
    private lateinit var cbMakan: CheckBox
    private lateinit var cbTidur: CheckBox
    private lateinit var cbOlahraga: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_relatif)

        init()

        btnSimpan.setOnClickListener {

            val nama = etNama.text.toString()
            val nomor = etNomor.text.toString()
            val alamat = etAlamat.text.toString()

            // Ambil teks RadioButton, bukan ID
            val selectedKelaminId = rgKelamin.checkedRadioButtonId
            val kelamin = if (selectedKelaminId != -1) {
                findViewById<RadioButton>(selectedKelaminId).text.toString()
            } else {
                "Tidak dipilih"
            }

            val agama = spAgama.selectedItem.toString()

            val hobi = mutableListOf<String>()
            if (cbMembaca.isChecked) hobi.add("Membaca")
            if (cbMakan.isChecked) hobi.add("Makan")
            if (cbTidur.isChecked) hobi.add("Tidur")
            if (cbOlahraga.isChecked) hobi.add("Olahraga")

            val hobiString = hobi.joinToString(", ")

            val keHasil = Intent(this, HasilForm::class.java)
            keHasil.putExtra("Nama", nama)
            keHasil.putExtra("Nomor", nomor)
            keHasil.putExtra("Alamat", alamat)
            keHasil.putExtra("Kelamin", kelamin)
            keHasil.putExtra("Agama", agama)
            keHasil.putExtra("Hobi", hobiString)

            startActivity(keHasil)
        }
    }

    private fun init() {
        etNama = findViewById(R.id.etNama)
        etNomor = findViewById(R.id.etNomor)
        etAlamat = findViewById(R.id.etAlamat)
        btnSimpan = findViewById(R.id.btnSimpan)
        rgKelamin = findViewById(R.id.rgKelamin)
        spAgama = findViewById(R.id.spAgama)
        cbMembaca = findViewById(R.id.cbMembaca)
        cbMakan = findViewById(R.id.cbMakan)
        cbTidur = findViewById(R.id.cbTidur)
        cbOlahraga = findViewById(R.id.cbOlahraga)
    }
}
