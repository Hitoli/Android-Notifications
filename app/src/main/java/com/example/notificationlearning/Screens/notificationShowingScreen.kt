package com.example.notificationlearning.Screens

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.notificationlearning.utils.Screen
import com.example.notificationlearning.ViewModel.MainViewModel

@Composable
fun NotificationShowingScreen(mainViewModel: MainViewModel= hiltViewModel(),context: Context,navcontroller:NavHostController) {
Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
    Button(onClick = { mainViewModel.showSimpleNotification(context = context ) }) {
        Text(text = "Show Notification")
    }
    Button(onClick = {mainViewModel.updateSimpleNotification(context=context)}) {
        Text(text = "Update Notification")
    }
    Button(onClick = {mainViewModel.cancelSimpleNotification()}) {
        Text(text = "Cancel Notification")
    }
    Button(onClick = {mainViewModel.showProgress(context = context)}) {
        Text(text = "Progress Notification")
    }
    Button(onClick = {mainViewModel.replySimpleNotification(context = context)}) {
        Text(text = "reply Notification")
    }
    Button(onClick = {navcontroller.navigate(
        Screen.Details.passArgument(
            message = "Coming from Notification Showing Screen"
        )
    )}){
        Text(text = "NEW SCREEN")
    }
}    
}