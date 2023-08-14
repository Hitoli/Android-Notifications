package com.example.notificationlearning

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
import com.example.notificationlearning.ViewModel.MainViewModel
import dagger.hilt.android.qualifiers.ApplicationContext

@Composable
fun NotificationShowingScreen(mainViewModel: MainViewModel= hiltViewModel(),context: Context) {
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
}    
}