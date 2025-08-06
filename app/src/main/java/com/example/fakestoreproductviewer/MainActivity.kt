package com.example.fakestoreproductviewer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.fakestoreproductviewer.ui.theme.FakeStoreProductViewerTheme
import androidx.compose.runtime.*
import androidx.navigation.compose.*


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FakeStoreProductViewerTheme {
                val navController = rememberNavController()
                val viewModel = remember { ProductViewModel() }

                NavHost(navController = navController, startDestination = "home") {
                    composable("home") {
                        HomeScreen(viewModel = viewModel) { selectedProduct ->
                            navController.currentBackStackEntry
                                ?.savedStateHandle
                                ?.set("product", selectedProduct)
                            navController.navigate("detail")
                        }
                    }
                    composable("detail") {
                        val product =
                            navController.previousBackStackEntry
                                ?.savedStateHandle
                                ?.get<Product>("product")
                        product?.let {
                            DetailScreen(it)
                        }
                    }
                }
            }
        }
    }
}