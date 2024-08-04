package com.example.fakeproducts.ViewModels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fakeproducts.Contracts.apiService
import com.example.fakeproducts.Models.Product
import kotlinx.coroutines.launch

class ProductViewModel: ViewModel() {
    private val _productsState = mutableStateOf(ProductsState())

    val productsState: State<ProductsState> = _productsState

    init {
        fetchProducts()
    }

    private fun fetchProducts() {
        viewModelScope.launch {
            try {
                val response = apiService.getProducts()

                _productsState.value = _productsState.value.copy(
                    list = response,
                    error = null,
                    loading = false,
                )
            } catch(e: Exception) {
                _productsState.value = _productsState.value.copy(
                    error = e.message,
                    loading = false,
                )
            }
        }
    }

    data class ProductsState(
        val list: List<Product> = emptyList(),
        val error: String? = null,
        val loading: Boolean = true,
    )
}