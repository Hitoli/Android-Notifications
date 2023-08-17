package com.example.notificationlearning.utils

import com.example.notificationlearning.navigation.MY_ARG

sealed class Screen(val route:String){
    object Main: Screen(route = "main")
    object Details: Screen(route = "Details/{$MY_ARG}"){
        fun passArgument(message:String)="Details/$message"
    }
}

