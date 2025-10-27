package com.example.uii_t8_demo_de_prototipo_funcional.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.uii_t8_demo_de_prototipo_funcional.model.Item
import com.example.uii_t8_demo_de_prototipo_funcional.model.User

class MainViewModel : ViewModel() {

    private val _items = mutableStateListOf<Item>()
    val items: List<Item> get() = _items

    // Lista de usuarios para login (hardcoded)
    private val _users = mutableStateListOf<User>()
    val users: List<User> get() = _users

    // Estado de autenticación
    private var _isAuthenticated = false
    val isAuthenticated: Boolean get() = _isAuthenticated

    init {
        // Inicialización básica
        _users.add(User("admin", "admin", "admin@example.com"))
    }
    
    // Función para inicializar datos de ejemplo
    fun initializeExampleData() {
        if (_items.isEmpty()) {
            _items.addAll(
                listOf(
                    Item(1, "Rudy", "Raza: Labrador Retriever | Género: Macho | Edad: 3 años", "Perro"),
                    Item(2, "Mimi", "Raza: Siamés | Género: Hembra | Edad: 2 años", "Gato"),
                    Item(3, "Rocky", "Raza: Bulldog | Género: Macho | Edad: 5 años", "Perro"),
                    Item(4, "Lola", "Raza: Golden Retriever | Género: Hembra | Edad: 4 años", "Perro"),
                    Item(5, "Paco", "Raza: Periquito | Género: Macho | Edad: 1 año", "Ave")
                )
            )
        }
    }

    // Función para validar login
    fun login(username: String, password: String): Boolean {
        val user = _users.find { it.username == username && it.password == password }
        _isAuthenticated = user != null
        return _isAuthenticated
    }

    // Funciones CRUD (simuladas)
    fun addItem(item: Item) {
        _items.add(item)
    }

    fun updateItem(item: Item) {
        val index = _items.indexOfFirst { it.id == item.id }
        if (index >= 0) {
            _items[index] = item
        }
    }

    fun deleteItem(id: Int) {
        _items.removeIf { it.id == id }
    }
    
    fun getItemById(id: Int): Item? {
        return _items.find { it.id == id }
    }
}