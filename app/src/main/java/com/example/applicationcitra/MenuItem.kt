package com.example.applicationcitra

data class MenuItem(
    // ⬇ Ini adalah data yang disimpan untuk setiap item pesanan ⬇
    val nama: String,
    val harga: Int,
    val quantity: Int
) {
    // Properti yang membantu menampilkan format "Red Velvet x1"
    val namaDanQuantity: String
        get() = "$nama x$quantity"

    // Properti yang menghitung harga total untuk item ini
    val subtotal: Int
        get() = harga * quantity
}