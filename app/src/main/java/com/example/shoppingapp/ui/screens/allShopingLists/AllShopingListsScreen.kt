package com.example.shoppingapp.ui.screens.allShopingLists

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Send
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.material3.*
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.shoppingapp.models.repositories.ProductWithQuantity
import com.example.shoppingapp.ui.theme.ShoppingAppTheme
import com.example.shoppingapp.viewmodels.AllShoppingListsViewModel
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.shoppingapp.R
import com.example.shoppingapp.ui.screens.allShopingLists.AllShoppingListsUiState
import com.example.shoppingapp.models.db.entities.ShoppingList
import com.example.shoppingapp.ui.screens.favouriteshops.ShopItem
import com.example.shoppingapp.ui.screens.shoppingList.ProductItem
import com.example.shoppingapp.ui.theme.ShoppingAppTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AllShoppingListsScreen(
    navController: NavController,
    viewModel: AllShoppingListsViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.fetchShoppingLists()
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("Dodaj produkt")  }
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = context.getString(R.string.route_shopping_lists)
                )
            }
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier.padding(innerPadding)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                items(uiState.shoppingLists) { shoppinglist ->
                    ShoppingListItem(shoppinglist, navController)
                }
            }
        }
    }
}

@Composable
fun ShoppingListItem(shoppinglist: ShoppingList, navController: NavController) {
    Text(
        text = shoppinglist.name,
        modifier = Modifier
            .padding(16.dp)
            .clickable {
                navController.navigate("Przejdź do listy")
            }
    )
}


@Preview(showBackground = true)
@Composable
fun AllShoppingListsScreenPreview() {
    AllShoppingListsScreen(
        navController = rememberNavController()
    )
}


