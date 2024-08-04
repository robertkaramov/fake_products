package com.example.fakeproducts.Contracts

import com.example.fakeproducts.Models.Product
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET

private val retrofit = Retrofit
    .Builder()
    .baseUrl("https://api.escuelajs.co/api/v1/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val apiService = retrofit.create<ApiService>()

interface ApiService {
    @GET("products")
    suspend fun getProducts(): List<Product>
}