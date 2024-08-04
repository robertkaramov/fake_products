package com.example.fakeproducts.Views

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.fakeproducts.Models.Product
import com.example.fakeproducts.ViewModels.ProductViewModel

@Composable
fun ProductsMainView(
    viewState: ProductViewModel.ProductsState,
    navigateToDetail: (product: Product) -> Unit,
) {
    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        when {
            viewState.loading -> {
                LoadingView()
            }

            viewState.error != null -> {
                Text("Error: ${viewState.error}")
            }

            else -> {
                ProductsView(
                    products = viewState.list,
                    navigateToDetail
                )
            }
        }
    }
}

@Composable
fun LoadingView() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        CircularProgressIndicator(
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Text("Loading ...")
    }
}

@Composable
fun ProductsView(
    products: List<Product>,
    navigateToDetail: (product: Product) -> Unit,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize(),
        ) {
        items(products) { product ->
            ProductView(product, navigateToDetail)
        }
    }
}

@Composable
fun ProductView(
    product: Product,
    navigateToDetail: (product: Product) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .clickable { navigateToDetail(product) },
    ) {
        Image(
            painter = rememberAsyncImagePainter(product.images.first()),
            contentDescription = null,
            modifier = Modifier.fillMaxSize().aspectRatio(1f)
        )
        Text(product.title)
        Text("$${product.price}")
    }
}

@Preview(showBackground = true)
@Composable
fun ProductsMainViewPreview() {
//    ProductsMainView({})
}