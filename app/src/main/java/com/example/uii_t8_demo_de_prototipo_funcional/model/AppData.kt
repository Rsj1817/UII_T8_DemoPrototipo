package com.example.uii_t8_demo_de_prototipo_funcional.model

// Modelo de datos principal para la aplicación
data class Item(
    val id: Int,
    val name: String,
    val description: String,
    val category: String,
    val imageUrl: String? = null
)

// Datos de usuario para el login
data class User(
    val username: String,
    val password: String,
    val email: String
)