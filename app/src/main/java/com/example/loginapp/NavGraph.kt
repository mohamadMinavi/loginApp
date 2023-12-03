package com.example.loginapp


import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun Nav(){

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "Home"){
        composable(route = "Home"){
            LoginScreen(navController)
        }
        composable(
            route = "Details?name={name}&date={date}&code={code}",
            arguments = listOf(
                navArgument(name = "name"){
                    type = NavType.StringType
                    defaultValue = "unknown"
                },
                navArgument(name = "date"){
                    type = NavType.StringType
                    defaultValue = "no date"
                },
                navArgument(name = "code"){
                    type = NavType.StringType
                    defaultValue = "no number"
                }
            )
        ){NavBackStackEntry ->

            DetailsScreen(
                myName = NavBackStackEntry.arguments?.getString("name"),
                myDate = NavBackStackEntry.arguments?.getString("date"),
                myCode = NavBackStackEntry.arguments?.getString("code")
            )

        }
    }
}