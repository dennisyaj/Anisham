package com.moncayo.pilco.anisham

data class Emoji(
    val category: String,
    val group: String,
    val htmlCode: List<String>,
    val name: String,
    val unicode: List<String>
)