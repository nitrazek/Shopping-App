package com.example.shoppingapp.ui.screens.favouriteshops

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.shoppingapp.R
import com.example.shoppingapp.viewmodels.FavouriteShopsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavouriteShopsScreen(
    navController: NavController,
    viewModel: FavouriteShopsViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current
    var searchQuery = remember { mutableStateOf("") }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(context.getString(R.string.route_add_shop)) }
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = context.getString(R.string.route_add_shop)
                )
            }
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding),
            color = MaterialTheme.colorScheme.background
        ) {
            OutlinedTextField(
                value = searchQuery.value,
                onValueChange = { searchQuery = mutableStateOf(it) },
                label = { Text(context.getString(R.string.search_hint))},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )

            LazyColumn(
                modifier = Modifier.padding(15.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                val filteredShops = uiState.shops.filter {
                    it.shop.name.contains(searchQuery.value, ignoreCase = true)
                }
                items(filteredShops) { shop ->
                    ShopItem(shop)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FavouriteShopsScreenPreview() {
    //FavouriteShopsScreen()
}