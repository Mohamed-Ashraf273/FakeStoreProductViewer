package com.example.fakestoreproductviewer

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(viewModel: ProductViewModel, onProductClick: (Product) -> Unit) {
    val state by viewModel.products.collectAsState()

    when (state) {
        is Result.Loading -> {
            Box(Modifier.fillMaxSize(), contentAlignment = androidx.compose.ui.Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        is Result.Error -> {
            Snackbar {
                Text(text = (state as Result.Error).message)
            }
        }

        is Result.Success -> {
            val products = (state as Result.Success<List<Product>>).data
            LazyColumn {
                items(products) { product ->
                    ProductCard(product = product, onClick = { onProductClick(product) })
                }
            }
        }
    }
}
