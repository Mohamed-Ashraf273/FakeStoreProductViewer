package com.example.fakestoreproductviewer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProductViewModel : ViewModel() {
    private val _products = MutableStateFlow<Result<List<Product>>>(Result.Loading)
    val products: StateFlow<Result<List<Product>>> = _products

    init {
        fetchProducts()
    }

    private fun fetchProducts() {
        viewModelScope.launch {
            try {
                val data = ProductApi.fetchProducts()
                _products.value = Result.Success(data)
            } catch (e: Exception) {
                _products.value = Result.Error("Failed to load products: ${e.message}")
            }
        }
    }
}
