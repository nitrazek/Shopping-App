package com.example.shoppingapp.ui.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.shoppingapp.R
import com.example.shoppingapp.ui.screens.addShop.AddShopScreen
import com.example.shoppingapp.ui.screens.favouriteshops.FavouriteShopsScreen
import com.example.shoppingapp.ui.screens.scanner.ScannerScreen
import com.example.shoppingapp.ui.screens.shoppingList.ShoppingListsScreen
import com.example.shoppingapp.ui.theme.ShoppingAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShoppingAppTheme {
                ShoppingApp()
            }
        }
    }
}

data class BottomNavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
)

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun ShoppingApp() {
    val navController = rememberNavController()
    val context = LocalContext.current

    val navItems = listOf(
        BottomNavigationItem(
            title = stringResource(id = R.string.route_shopping_lists),
            selectedIcon = Icons.Filled.ShoppingCart,
            unselectedIcon = Icons.Outlined.ShoppingCart
        ),
        BottomNavigationItem(
            title = stringResource(id = R.string.route_scanner),
            selectedIcon = Icons.Filled.Search,
            unselectedIcon = Icons.Outlined.Search
        ),
        BottomNavigationItem(
            title = stringResource(id = R.string.route_favourite_shops),
            selectedIcon = Icons.Filled.Favorite,
            unselectedIcon = Icons.Outlined.Favorite
        )
    )

    val selectedItemIndex = rememberSaveable { mutableIntStateOf(0) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Scaffold(
            bottomBar = {
                NavigationBar {
                    navItems.forEachIndexed { index, item ->
                        NavigationBarItem(
                            selected = selectedItemIndex.intValue == index,
                            onClick = {
                                selectedItemIndex.intValue = index
                                navController.navigate(item.title)
                            },
                            alwaysShowLabel = true,
                            icon = {
                                Icon(
                                    imageVector = if(selectedItemIndex.intValue == index) {
                                        item.selectedIcon
                                    } else item.unselectedIcon,
                                    contentDescription = item.title,
                                )
                            }
                        )
                    }
                }
            }
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = stringResource(R.string.route_shopping_lists),
                modifier = Modifier.padding(innerPadding)
            ) {
                composable(context.getString(R.string.route_shopping_lists)) { ShoppingListsScreen() }
                composable(context.getString(R.string.route_scanner)) { ScannerScreen() }
                composable(context.getString(R.string.route_favourite_shops)) { FavouriteShopsScreen(navController) }
                composable(context.getString(R.string.route_add_shop)) { AddShopScreen() }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ShoppingAppPreview() {
    ShoppingAppTheme {
        ShoppingApp()
    }
}