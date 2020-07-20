package kz.ain.zapis.networking.models

data class Firm(
    val id: Int,
    val name: String,
    val address: String,
    val type: String,
    val avatarUrl: String,
    val pictures: List<String>
)

