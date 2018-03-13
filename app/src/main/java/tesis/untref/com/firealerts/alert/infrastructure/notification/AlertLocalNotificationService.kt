package tesis.untref.com.firealerts.alert.infrastructure.notification

import android.app.NotificationManager
import android.content.Context
import android.support.v4.app.NotificationCompat
import tesis.untref.com.firealerts.R
import java.util.*

class AlertLocalNotificationService(private val context: Context, private val random: Random) {

    fun sendNotification() {
        val notificationBuilder = NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.ic_stat_alert_notification)
                .setContentTitle("ALERTA DE INCENDIO")
                .setContentText("Obtén la ubicación ingrensando a la app")
        val notificationId = generateId()
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(notificationId, notificationBuilder.build())
    }

    private fun generateId(): Int = (random.nextDouble() * MULTIPLIER).toInt()

    companion object {
        const val MULTIPLIER = 1000000
    }
}