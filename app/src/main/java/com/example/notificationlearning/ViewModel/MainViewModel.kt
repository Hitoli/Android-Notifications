package com.example.notificationlearning.ViewModel

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationCompat.Builder
import androidx.core.app.NotificationManagerCompat
import androidx.core.app.Person
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.viewModelScope
import com.example.notificationlearning.DI.NotificationModule
import com.example.notificationlearning.receiver.BroadcastReceive
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

import javax.inject.Inject

@HiltViewModel

class MainViewModel @Inject constructor(
    @NotificationModule.MainNotification
    private val notificationBuilder: NotificationCompat.Builder,
    @NotificationModule.ThirdNotification
    private val notificationThirdBuilder: NotificationCompat.Builder,
    private val notificationManagerCompat: NotificationManagerCompat,
    @NotificationModule.SecondNotification
    private val provideSecondNotificationBuilder:NotificationCompat.Builder):ViewModel() {
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
    fun replySimpleNotification(context: Context){
        val person = Person.Builder().setName("Me").build()
        val intent = Intent(context, BroadcastReceive::class.java)
        val input = intent.getStringExtra("Input")
        val message = NotificationCompat.MessagingStyle.Message(input,System.currentTimeMillis(),person)
        val notificaitonStyle = NotificationCompat.MessagingStyle(person).addMessage(message)
        NotificationCompat.MessagingStyle(person).addMessage(message)
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        notificationManagerCompat.notify(314,notificationThirdBuilder.setStyle(notificaitonStyle).setStyle(null).build()
        )

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

    fun showProgress(context: Context){
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
        val max = 10
        var progress=0
        viewModelScope.launch {
            while (progress!=max){
                delay(1000)
                progress+=1
                notificationManagerCompat.notify(4,
                    provideSecondNotificationBuilder
                    .setContentTitle("Downloading")
                    .setContentText("${progress}/${max}")
                    .setProgress(max,progress,false).build()
                )
            }
            notificationManagerCompat.notify(14,notificationBuilder
                .setContentTitle("Completed")
                .setContentText("")
                .setContentIntent(null)
                .clearActions()
                .setProgress(0,0,false).build())

        }

    }
}