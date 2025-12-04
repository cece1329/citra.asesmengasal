package com.example.applicationcitra

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class cafe : AppCompatActivity() {

    // Deklarasi variabel
    private var etNama: EditText? = null
    private var btnPesan: Button? = null

    // Array untuk menyimpan quantity tiap item
    private val quantities = IntArray(10)

    // Array nama menu dan harga
    private val menuNames = arrayOf(
        "Red Velvet", "Chocolate Fudgy Brownies", "StrawBerry ShortCake",
        "CheeseCake", "Mochi Donut", "MilkTea Boba", "Strawberry Milk",
        "Chocolate Boba", "MatchaLatte Boba", "BrownSugar Boba"
    )

    private val menuPrices = intArrayOf(
        35000, 28000, 30000, 36000, 25000,
        20000, 25000, 20000, 25000, 25000
    )

    // Array TextView untuk quantity
    private val tvQuantities = arrayOfNulls<TextView>(10)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cafe)

        // Inisialisasi views
        etNama = findViewById(R.id.etNama)
        btnPesan = findViewById(R.id.btnPesan)

        // Inisialisasi TextView quantity
        tvQuantities[0] = findViewById(R.id.tvQuantity1)
        tvQuantities[1] = findViewById(R.id.tvQuantity2)
        tvQuantities[2] = findViewById(R.id.tvQuantity3)
        tvQuantities[3] = findViewById(R.id.tvQuantity4)
        tvQuantities[4] = findViewById(R.id.tvQuantity5)
        tvQuantities[5] = findViewById(R.id.tvQuantity6)
        tvQuantities[6] = findViewById(R.id.tvQuantity7)
        tvQuantities[7] = findViewById(R.id.tvQuantity8)
        tvQuantities[8] = findViewById(R.id.tvQuantity9)
        tvQuantities[9] = findViewById(R.id.tvQuantity10)

        // Setup button listeners
        setupButtonListeners()

        // Tombol Pesan
        btnPesan?.setOnClickListener {
            prosesOrder()
        }
    }

    private fun setupButtonListeners() {
        // Item 1 - Red Velvet
        findViewById<Button>(R.id.btnPlus1).setOnClickListener { updateQuantity(0, 1) }
        findViewById<Button>(R.id.btnMinus1).setOnClickListener { updateQuantity(0, -1) }

        // Item 2 - Brownies
        findViewById<Button>(R.id.btnPlus2).setOnClickListener { updateQuantity(1, 1) }
        findViewById<Button>(R.id.btnMinus2).setOnClickListener { updateQuantity(1, -1) }

        // Item 3 - Strawberry Cake
        findViewById<Button>(R.id.btnPlus3).setOnClickListener { updateQuantity(2, 1) }
        findViewById<Button>(R.id.btnMinus3).setOnClickListener { updateQuantity(2, -1) }

        // Item 4 - Cheesecake
        findViewById<Button>(R.id.btnPlus4).setOnClickListener { updateQuantity(3, 1) }
        findViewById<Button>(R.id.btnMinus4).setOnClickListener { updateQuantity(3, -1) }

        // Item 5 - Mochi Donut
        findViewById<Button>(R.id.btnPlus5).setOnClickListener { updateQuantity(4, 1) }
        findViewById<Button>(R.id.btnMinus5).setOnClickListener { updateQuantity(4, -1) }

        // Item 6 - MilkTea
        findViewById<Button>(R.id.btnPlus6).setOnClickListener { updateQuantity(5, 1) }
        findViewById<Button>(R.id.btnMinus6).setOnClickListener { updateQuantity(5, -1) }

        // Item 7 - Strawberry Milk
        findViewById<Button>(R.id.btnPlus7).setOnClickListener { updateQuantity(6, 1) }
        findViewById<Button>(R.id.btnMinus7).setOnClickListener { updateQuantity(6, -1) }

        // Item 8 - Chocolate Boba
        findViewById<Button>(R.id.btnPlus8).setOnClickListener { updateQuantity(7, 1) }
        findViewById<Button>(R.id.btnMinus8).setOnClickListener { updateQuantity(7, -1) }

        // Item 9 - MatchaLatte
        findViewById<Button>(R.id.btnPlus9).setOnClickListener { updateQuantity(8, 1) }
        findViewById<Button>(R.id.btnMinus9).setOnClickListener { updateQuantity(8, -1) }

        // Item 10 - BrownSugar
        findViewById<Button>(R.id.btnPlus10).setOnClickListener { updateQuantity(9, 1) }
        findViewById<Button>(R.id.btnMinus10).setOnClickListener { updateQuantity(9, -1) }
    }

    // Fungsi update quantity
    private fun updateQuantity(itemIndex: Int, change: Int) {
        quantities[itemIndex] += change

        // Pastikan tidak kurang dari 0
        if (quantities[itemIndex] < 0) {
            quantities[itemIndex] = 0
        }

        // Update TextView
        tvQuantities[itemIndex]?.text = quantities[itemIndex].toString()
    }

    // Fungsi proses order
    private fun prosesOrder() {
        val nama = etNama?.text.toString().trim()

        // Validasi nama
        if (nama.isEmpty()) {
            Toast.makeText(this, "Masukkan nama customer terlebih dahulu!", Toast.LENGTH_SHORT).show()
            return
        }

        // Hitung total item dan harga
        var totalItems = 0
        var totalHarga = 0
        val pesanan = StringBuilder()

        for (i in quantities.indices) {
            if (quantities[i] > 0) {
                totalItems += quantities[i]
                val subtotal = quantities[i] * menuPrices[i]
                totalHarga += subtotal

                pesanan.append("${menuNames[i]} x${quantities[i]} = Rp ${formatRupiah(subtotal)}\n")
            }
        }

        // Validasi apakah ada pesanan
        if (totalItems == 0) {
            Toast.makeText(this, "Pilih menu terlebih dahulu!", Toast.LENGTH_SHORT).show()
            return
        }

        // Tampilkan dialog konfirmasi
        tampilkanKonfirmasi(nama, pesanan.toString(), totalHarga)
    }

    // Fungsi tampilkan dialog konfirmasi
    private fun tampilkanKonfirmasi(nama: String, pesanan: String, totalHarga: Int) {
        val message = """
            Nama: $nama
            
            Pesanan:
            $pesanan
            Total: Rp ${formatRupiah(totalHarga)}
        """.trimIndent()

        AlertDialog.Builder(this)
            .setTitle("Konfirmasi Pesanan")
            .setMessage(message)
            .setPositiveButton("Konfirmasi") { _, _ ->
                // Pindah ke halaman nota
                val intent = Intent(this, NotaActivity::class.java).apply {
                    putExtra("NAMA", nama)
                    putExtra("PESANAN", pesanan)
                    putExtra("TOTAL", totalHarga)
                }
                startActivity(intent)

                // Reset form setelah pesan
                resetForm()
            }
            .setNegativeButton("Batal", null)
            .show()
    }

    // Fungsi reset form
    private fun resetForm() {
        etNama?.setText("")
        for (i in quantities.indices) {
            quantities[i] = 0
            tvQuantities[i]?.text = "0"
        }
    }

    // Fungsi format rupiah
    private fun formatRupiah(angka: Int): String {
        return String.format("%,d", angka).replace(',', '.')
    }
}