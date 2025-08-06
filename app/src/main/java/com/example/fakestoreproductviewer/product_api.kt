package com.example.fakestoreproductviewer

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import io.ktor.client.engine.okhttp.*


object ProductApi {
    private val client = HttpClient(OkHttp) {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
    }

    suspend fun fetchProducts(): List<Product> {
        try {
            val response = client.get("https://api.escuelajs.co/api/v1/products")
            if (response.status.value in 200..299) {
                return response.body()
            } else {
                throw Exception("HTTP ${response.status.value}: ${response.status.description}")
            }
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
    }

}
