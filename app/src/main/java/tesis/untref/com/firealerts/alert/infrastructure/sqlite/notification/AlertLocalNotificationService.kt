package tesis.untref.com.firealerts.alert.infrastructure.sqlite.notification

import android.app.NotificationManager
import android.content.Context
import android.support.v4.app.NotificationCompat
import tesis.untref.com.firealerts.R
import java.util.*

class AlertLocalNotificationService(private val context: Context, private val random: Random) {

    fun sendNotification() {
        val notificationBuilder = NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.notification_icon_background)
                .setContentTitle("My notification")
                .setContentText("Hello World!")
        val notificationId = generateId()
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(notificationId, notificationBuilder.build())
    }

    private fun generateId(): Int = (random.nextDouble() * MULTIPLIER).toInt()

    companion object {
        const val MULTIPLIER = 1000000
    }
}