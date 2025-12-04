package com.example.applicationcitra

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.NumberFormat // Import baru
import java.text.SimpleDateFormat
import java.util.*

class NotaActivity : AppCompatActivity() {

    private lateinit var tvNotaNama: TextView
    private lateinit var tvNotaNumber: TextView
    private lateinit var tvNotaTanggal: TextView
    private lateinit var layoutPesanan: LinearLayout
    private lateinit var tvNotaTotal: TextView
    private lateinit var btnShare: Button
    private lateinit var btnKembali: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nota)

        // Inisialisasi views
        tvNotaNama = findViewById(R.id.tvNotaNama)
        tvNotaNumber = findViewById(R.id.tvNotaNumber)
        tvNotaTanggal = findViewById(R.id.tvNotaTanggal)
        layoutPesanan = findViewById(R.id.layoutPesanan)
        tvNotaTotal = findViewById(R.id.tvNotaTotal)
        btnShare = findViewById(R.id.btnShare)
        btnKembali = findViewById(R.id.btnKembali)

        // Ambil data dari intent
        val nama = intent.getStringExtra("NAMA") ?: "Pelanggan"
        val pesanan = intent.getStringExtra("PESANAN") ?: ""
        val total = intent.getIntExtra("TOTAL", 0)

        // Set data
        tvNotaNama.text = nama
        tvNotaNumber.text = generateNotaNumber()
        tvNotaTanggal.text = getCurrentDateTime()

        // KOREKSI 1: Menggunakan formatRupiah baru yang sudah mencakup "Rp"
        tvNotaTotal.text = formatRupiah(total)

        // Parse dan tampilkan pesanan
        tampilkanPesanan(pesanan)

        // Button listeners
        btnShare.setOnClickListener {
            // Mengirim data 'total' yang sudah di-format ke share function
            shareNota(nama, pesanan, total)
        }

        btnKembali.setOnClickListener {
            finish() // Kembali ke halaman sebelumnya
        }
    }

    private fun tampilkanPesanan(pesanan: String) {
        // Hapus tampilan lama sebelum menambahkan yang baru (praktik yang baik)
        layoutPesanan.removeAllViews()

        // Split pesanan berdasarkan newline
        val items = pesanan.trim().split("\n")

        for (item in items) {
            if (item.isNotEmpty()) {
                // Buat view untuk setiap item (menggunakan item_nota.xml)
                val itemView = LayoutInflater.from(this)
                    .inflate(R.layout.item_nota, layoutPesanan, false)

                val tvItemNama = itemView.findViewById<TextView>(R.id.tvItemNama)
                val tvItemHarga = itemView.findViewById<TextView>(R.id.tvItemHarga)

                // Parsing item (format yang diharapkan: "Nama x2 = Rp 50.000")
                val parts = item.split(" = ")

                // KOREKSI 2: Validasi Parsing
                // Memastikan hasil split memiliki 2 bagian sebelum mencoba mengakses parts[1]
                if (parts.size == 2) {
                    tvItemNama.text = parts[0].trim()
                    // Pastikan harga juga di-trim
                    tvItemHarga.text = parts[1].trim()
                } else {
                    // Fallback: Jika gagal parsing, tampilkan seluruh baris di Nama
                    tvItemNama.text = item.trim()
                    tvItemHarga.text = ""
                }

                layoutPesanan.addView(itemView)
            }
        }
    }

    private fun generateNotaNumber(): String {
        val random = (1000..9999).random()
        return "#INV$random"
    }

    private fun getCurrentDateTime(): String {
        // KOREKSI 3: Gunakan Locale("id", "ID") untuk format tanggal Indonesia yang benar
        val sdf = SimpleDateFormat("dd MMM yyyy, HH:mm", Locale("id", "ID"))
        return sdf.format(Date())
    }

    // KOREKSI 4: Menggunakan NumberFormat untuk format Rupiah yang benar dan aman
    private fun formatRupiah(angka: Int): String {
        // Menggunakan NumberFormat dengan Locale Indonesia
        val localeID = Locale("in", "ID")
        val formatRupiah = NumberFormat.getCurrencyInstance(localeID)

        // Hasil format biasanya "Rp1.000.000,00". Kita hapus ,00 dan tambahkan spasi setelah Rp
        return formatRupiah.format(angka)
            .replace(",00", "")
            .replace("Rp", "Rp ")
            .trim()
    }

    private fun shareNota(nama: String, pesanan: String, total: Int) {
        val notaText = """
            ═══════════════════════════
            ☕ SIBOBA CAFE ☕
            ═══════════════════════════
            
            Nama: $nama
            No. Nota: ${tvNotaNumber.text}
            Tanggal: ${tvNotaTanggal.text}
            
            ───────────────────────────
            PESANAN:
            $pesanan
            ───────────────────────────
            
            // KOREKSI 5: Menggunakan formatRupiah baru
            TOTAL: ${formatRupiah(total)}
            
            ═══════════════════════════
            Terima kasih atas kunjungan Anda!
            Selamat menikmati! ☕🧋
            ═══════════════════════════
        """.trimIndent()

        val shareIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, notaText)
            type = "text/plain"
        }
        startActivity(Intent.createChooser(shareIntent, "Bagikan Nota via"))
    }
}