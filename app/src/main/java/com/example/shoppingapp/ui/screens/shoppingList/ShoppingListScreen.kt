package com.example.shoppingapp.ui.screens.shoppingList

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.shoppingapp.models.repositories.ProductWithQuantity
import com.example.shoppingapp.ui.theme.ShoppingAppTheme
import com.example.shoppingapp.viewmodels.ShoppingListViewModel
import androidx.core.content.ContextCompat.startActivity
import com.example.shoppingapp.R
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingListScreen(
    navController: NavController,
    viewModel: ShoppingListViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.fetchItems()
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    val mapIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/maps"))
                    context.startActivity(mapIntent)
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.Send,
                    contentDescription = "Open map"
                )
            }
            FloatingActionButton(
                onClick = {
                    navController.navigate(context.getString(R.string.route_add_product))
                }) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Dodaj produkt"
                )
            }
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            color = MaterialTheme.colorScheme.background,
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                items(uiState.products) { product ->
                    ProductItem(product)
                }
            }
        }
    }
}

@Composable
fun ProductItem(product: ProductWithQuantity) {

    Text(text = product.product.name)

    Text(
        text = "Quantity: ${product.quantity}",
        color = MaterialTheme.colorScheme.primary
    )

}


@Preview(showBackground = true)
@Composable
fun ShoppingListScreenPreview() {
    ShoppingListScreen(navController = rememberNavController())
}

