package com.example.notificationlearning.receiver

import android.Manifest
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.app.Person
import androidx.core.app.RemoteInput
import com.example.notificationlearning.DI.NotificationModule
import com.example.notificationlearning.DI.RESULT_KEY
import com.example.notificationlearning.ViewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@AndroidEntryPoint
class BroadcastReceive:BroadcastReceiver() {

    @Inject
    lateinit var notificationmanager:NotificationManagerCompat

    @NotificationModule.ThirdNotification
    @Inject
    lateinit var notificationBuilder:NotificationCompat.Builder

    override fun onReceive(context: Context?, intent: Intent?) {
        val message = intent?.getStringExtra("MESSAGE")
        if(message!=null){
            Toast.makeText(context,message,Toast.LENGTH_LONG).show()
        }
        val remoteInput = RemoteInput.getResultsFromIntent(intent!!)
        if(remoteInput!=null){
            val input = remoteInput.getCharSequence(RESULT_KEY).toString()

        }
    }
}