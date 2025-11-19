package com.example.uii_t8_demo_de_prototipo_funcional.data

import android.content.Context
import com.example.uii_t8_demo_de_prototipo_funcional.model.Item

class ItemRepository(context: Context) {

    private val dbHelper = DatabaseHelper(context)

    fun getAll(): List<Item> = dbHelper.getAllItems()

    fun getById(id: Long): Item? = dbHelper.getItemById(id)

    fun insert(item: Item): Long = dbHelper.insertItem(item)

    fun update(item: Item): Int = dbHelper.updateItem(item)

    fun delete(id: Long): Int = dbHelper.deleteItem(id)
}
