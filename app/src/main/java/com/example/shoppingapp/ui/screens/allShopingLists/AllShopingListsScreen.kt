package com.example.shoppingapp.ui.screens.allShopingLists

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
import com.example.shoppingapp.viewmodels.AllShoppingListsViewModel
import androidx.core.content.ContextCompat.startActivity
import com.example.shoppingapp.ui.screens.allShopingLists.AllShoppingListsUiState
import com.example.shoppingapp.models.db.entities.ShoppingList
import com.example.shoppingapp.ui.theme.ShoppingAppTheme


@Composable
fun AllShoppingListsScreen(viewModel: AllShoppingListsViewModel = hiltViewModel()) {
     val uiState by viewModel.uiState.collectAsStateWithLifecycle()
     val context = LocalContext.current
}



@Preview(showBackground = true)
@Composable
fun AllShoppingListScreenPreview() {
    AllShoppingListsScreen()
}


