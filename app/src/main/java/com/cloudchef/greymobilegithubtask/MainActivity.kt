package com.cloudchef.greymobilegithubtask

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.cloudchef.greymobilegithubtask.presentation.BottomNavigation
import com.cloudchef.greymobilegithubtask.presentation.home.HomeScreen
import com.cloudchef.greymobilegithubtask.presentation.home.ScreenNav
import com.cloudchef.greymobilegithubtask.presentation.repositories_list.SearchScreen
import com.cloudchef.greymobilegithubtask.presentation.user_detail.UserScreen
import com.cloudchef.greymobilegithubtask.ui.theme.GreyMobileGithubTaskTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GreyMobileGithubTaskTheme {
                val navController = rememberNavController()
                var currentScreen by remember { mutableStateOf<ScreenNav>(ScreenNav.Home) }
                val navBackStackEntry by navController.currentBackStackEntryAsState()

                LaunchedEffect(navBackStackEntry) {
                    navBackStackEntry?.destination?.route?.let { route ->
                        currentScreen = ScreenNav.Items.list.find { it.id == route } ?: ScreenNav.Home
                    }
                }

                Scaffold(modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        BottomNavigation(currentScreenId = currentScreen.id) { it
                            currentScreen = it
                            navController.navigate(currentScreen.id) {
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = false
                                    inclusive = false
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    }) { innerPadding ->

                    NavHost(navController = navController, startDestination = ScreenNav.Home.id) {
                        composable(ScreenNav.Home.id) {
                            HomeScreen(modifier = Modifier.padding(innerPadding), navController)
                        }
                        composable(ScreenNav.Search.id) {
                            SearchScreen(modifier = Modifier.padding(innerPadding), navController = navController)
                        }
                        composable(ScreenNav.Users.id) {
                           UserScreen(modifier = Modifier.padding(innerPadding), navController = navController)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GreyMobileGithubTaskTheme {
        Greeting("Android")
    }
}