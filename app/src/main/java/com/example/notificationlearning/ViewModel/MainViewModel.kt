package com.example.notificationlearning.ViewModel

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext

import javax.inject.Inject

@HiltViewModel

class MainViewModel @Inject constructor(private val notificationBuilder: NotificationCompat.Builder,private val notificationManagerCompat: NotificationManagerCompat):ViewModel() {
    fun showSimpleNotification( context: Context){
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            Log.e("Permission Required","Permission Required")
            return
        }
        notificationManagerCompat.notify(314,notificationBuilder.build())
    }

    fun updateSimpleNotification(context: Context){
        if (ActivityCompat.checkSelfPermission(
                context, 
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            Log.e("Permission Required","Permission Required")
            return
        }
        notificationManagerCompat.notify(314,notificationBuilder.setContentTitle("UPDATED TEXT").build())
    }

    fun cancelSimpleNotification(){
        notificationManagerCompat.cancel(314)
    }
}