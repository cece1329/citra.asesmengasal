

package com.example.applicationcitra

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.NumberFormat
import java.util.*

class NotaAdapter(
    private val context: Context,
    private val items: List<MenuItem> // Daftar data pesanan yang mau ditampilkan
) : RecyclerView.Adapter<NotaAdapter.NotaViewHolder>() {

    // (Kode ViewHolder, onCreateViewHolder, getItemCount, dan onBindViewHolder seperti di jawaban sebelumnya)
    // ...
    // ...

    // Fungsi yang menghubungkan data ke tampilan (di dalam ViewHolder)
    inner class NotaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNama: TextView = itemView.findViewById(R.id.tvItemNama)
        val tvHarga: TextView = itemView.findViewById(R.id.tvItemHarga)

        fun bind(item: MenuItem) {
            // Mengambil data dari MenuItem dan memasukkannya ke TextView di item_nota.xml
            tvNama.text = item.namaDanQuantity // Contoh: "Red Velvet x1"
            tvHarga.text = formatRupiah(item.subtotal) // Contoh: "Rp 35.000"
        }

        private fun formatRupiah(angka: Int): String {
            // ... (Fungsi format Rupiah)
            val format = NumberFormat.getCurrencyInstance(Locale("in", "ID"))
            return format.format(angka).replace("Rp", "Rp ").substringBefore(',')
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotaViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_nota, parent, false)
        return NotaViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: NotaViewHolder, position: Int) {
        holder.bind(items[position])
    }
}