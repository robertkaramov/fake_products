package com.example.fakeproducts

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.fakeproducts.Models.Product
import com.example.fakeproducts.ViewModels.ProductViewModel
import com.example.fakeproducts.Views.ProductDetailView
import com.example.fakeproducts.Views.ProductsMainView

@Composable
fun MainRoute(
    navController: NavHostController
) {
    val productsViewModel: ProductViewModel = viewModel()
    val productsState by productsViewModel.productsState

    NavHost(
        navController = navController,
        startDestination = Screen.ProductMainView.route,
    ) {
        composable(route = Screen.ProductMainView.route) {
            ProductsMainView(viewState = productsState) {
                product ->
                navController.currentBackStackEntry?.savedStateHandle?.set(
                    "product",
                    product,
                )
                navController.navigate(Screen.ProductDetailView.route)
            }
        }

        composable(route = Screen.ProductDetailView.route) {
            // TODO: надо делать опциональным

            val product = navController
                .previousBackStackEntry
                ?.savedStateHandle
                ?.get<Product>("product")

            ProductDetailView(product = product!!)
        }
    }
}