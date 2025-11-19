package com.example.uii_t8_demo_de_prototipo_funcional.viewmodel

import android.app.Application
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.uii_t8_demo_de_prototipo_funcional.data.ItemRepository
import com.example.uii_t8_demo_de_prototipo_funcional.model.Item
import com.example.uii_t8_demo_de_prototipo_funcional.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(application: Application) : AndroidViewModel(application) {

    // Inicializamos el repository con el Application (es Context)
    private val repository: ItemRepository = ItemRepository(getApplication())

    // Lista observable para Compose
    private val _items = mutableStateListOf<Item>()
    val items: List<Item> get() = _items

    // Usuarios en memoria para login simple
    private val _users = mutableStateListOf<User>()
    val users: List<User> get() = _users

    private var _isAuthenticated = false
    val isAuthenticated: Boolean get() = _isAuthenticated

    init {
        // Usuario de ejemplo
        _users.add(User("admin", "admin", "admin@example.com"))
        // Carga inicial desde la BD
        refreshItemsFromDb()
    }

    private fun refreshItemsFromDb() {
        viewModelScope.launch {
            val list = withContext(Dispatchers.IO) {
                repository.getAll()
            }
            _items.clear()
            _items.addAll(list)
        }
    }

    /**
     * Login simple (usa la lista en memoria)
     */
    fun login(username: String, password: String): Boolean {
        val user = _users.find { it.username == username && it.password == password }
        _isAuthenticated = user != null
        return _isAuthenticated
    }

    /**
     * CREATE
     * onComplete devuelve el id insertado por si quieres usarlo
     */
    fun addItem(item: Item, onComplete: ((Long) -> Unit)? = null) {
        viewModelScope.launch {
            val id = withContext(Dispatchers.IO) {
                repository.insert(item)
            }
            // refrescar la lista para que Compose actualice la UI
            refreshItemsFromDb()
            onComplete?.invoke(id)
        }
    }

    /**
     * UPDATE
     */
    fun updateItem(item: Item, onComplete: ((Int) -> Unit)? = null) {
        viewModelScope.launch {
            val rows = withContext(Dispatchers.IO) {
                repository.update(item)
            }
            refreshItemsFromDb()
            onComplete?.invoke(rows)
        }
    }

    /**
     * DELETE
     */
    fun deleteItem(id: Long, onComplete: ((Int) -> Unit)? = null) {
        viewModelScope.launch {
            val rows = withContext(Dispatchers.IO) {
                repository.delete(id)
            }
            refreshItemsFromDb()
            onComplete?.invoke(rows)
        }
    }

    /**
     * Helper para obtener un item desde la lista cargada (no accede directo a DB)
     */
    fun getItemById(id: Long): Item? {
        return _items.find { it.id == id }
    }
}
