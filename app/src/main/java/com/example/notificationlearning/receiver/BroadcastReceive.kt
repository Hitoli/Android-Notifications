package com.example.notificationlearning.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class BroadcastReceive:BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val message = intent?.getStringExtra("MESSAGE")
        if(message!=null){
            Toast.makeText(context,message,Toast.LENGTH_LONG).show()
        }
    }
}