package com.example.notificationlearning.DI

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.net.toUri
import com.example.notificationlearning.MainActivity
import com.example.notificationlearning.R
import com.example.notificationlearning.navigation.MY_ARG
import com.example.notificationlearning.navigation.MY_URI
import com.example.notificationlearning.receiver.BroadcastReceive
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NotificationModule {
    @Singleton
    @Provides
    fun provideNotificaitonBuilder(
        @ApplicationContext context:Context
    ):NotificationCompat.Builder{
        val intent = Intent(context,BroadcastReceive::class.java).apply{
            putExtra("MESSAGE","Clicked aye")
        }

        val pendingIntent = PendingIntent.getBroadcast(
            context,
            0,
            intent,
            PendingIntent.FLAG_IMMUTABLE
        )
        val clickIntent = Intent(Intent.ACTION_VIEW,"$MY_URI/$MY_ARG=Coming from Notification".toUri(),context,MainActivity::class.java)
        val PendingIntents: PendingIntent = TaskStackBuilder.create(context).run {
            addNextIntentWithParentStack(clickIntent)
            getPendingIntent(1,PendingIntent.FLAG_IMMUTABLE)
        }
        return NotificationCompat.Builder(context,"Main Channel ID")
            .setContentTitle("Welcome")
            .setContentText("Welcome to my Twitter")
            .setSmallIcon(R.drawable.baseline_notifications_24)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .addAction(0,"ACTION",pendingIntent)
            .setContentIntent(PendingIntents)
    }

    @Singleton
    @Provides
    fun provideNotificationManager(
        @ApplicationContext context: Context
    ):NotificationManagerCompat{
        val notificationManager= NotificationManagerCompat.from(context)
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            val channel = NotificationChannel(
                "Main Channel ID","Main Channel",NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(channel)
        }
        return notificationManager
    }

}