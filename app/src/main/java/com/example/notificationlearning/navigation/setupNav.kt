package com.example.notificationlearning.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.example.notificationlearning.Screens.DetailsScreen
import com.example.notificationlearning.Screens.NotificationShowingScreen
import com.example.notificationlearning.utils.Screen

const val MY_URI = "https://stevdza-san.com"
const val MY_ARG= "message"
@Composable
fun setUpNavGraph(
    navcontroller:NavHostController,context: Context
) {
    NavHost(navController = navcontroller, startDestination = Screen.Main.route){
        composable(route = Screen.Main.route){
         NotificationShowingScreen( context = context, navcontroller = navcontroller)
        }
        composable(route = Screen.Details.route,
            arguments = listOf(navArgument(MY_ARG){type= NavType.StringType}),
            deepLinks = listOf(navDeepLink { uriPattern="$MY_URI/$MY_ARG={$MY_ARG}" })
        ){
            val arguments = it.arguments
            arguments?.getString(MY_ARG)?.let { message ->
                DetailsScreen(argument = message)
            }
        }
    }

}