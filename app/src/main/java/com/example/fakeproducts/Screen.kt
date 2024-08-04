package com.example.fakeproducts

sealed class Screen(
    val route: String,
) {
    object ProductMainView:Screen("list")
    object ProductDetailView:Screen("detail")
}