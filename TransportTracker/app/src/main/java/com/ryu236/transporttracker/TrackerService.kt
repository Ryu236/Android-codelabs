package com.ryu236.transporttracker

import android.app.PendingIntent
import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat

class TrackerService : Service() {

    private val TAG = TrackerService::class.java.simpleName

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        buildNotification()
        loginToFirebase()
    }

    private fun buildNotification() {
        val stop = "stop"
        registerReceiver(stopReceiver(), IntentFilter(stop))
        val broadcastIntent: PendingIntent = PendingIntent.getBroadcast(
            this, 0, Intent(stop), PendingIntent.FLAG_UPDATE_CURRENT
        )
        // Create the persistent notification
        val builder: NotificationCompat.Builder = NotificationCompat.Builder(this)
            .setContentTitle(getString(R.string.app_name))
            .setContentText(getString(R.string.notification_text))
            .setOngoing(true)
            .setContentIntent(broadcastIntent)
            .setSmallIcon(R.drawable.ic_tracker)
        startForeground(1, builder.build())
    }

    protected fun stopReceiver: BroadcastReceiver {
        override fun onReceive(context: Context, intent: Intent) {
            Log.d(TAG, "received stop broadcast")
            // Stop the service when the notification is tapped
            unregisterReceiver(stopReceiver())
            stopSelf()
        }
    }

    private fun loginToFirebase() {
        // TODO
    }

    private fun requestLocationUpdates() {
        //TODO
    }
}
