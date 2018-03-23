package tesis.untref.com.firealerts.alert.infrastructure.notification

import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.support.v4.app.NotificationCompat
import tesis.untref.com.firealerts.MainActivity
import tesis.untref.com.firealerts.R
import java.util.*
import android.app.PendingIntent
import android.net.Uri
import android.os.Vibrator
import android.support.v4.app.NotificationCompat.VISIBILITY_PUBLIC

class AlertLocalNotificationService(private val context: Context, private val random: Random) {

    fun sendNotification() {
        val notificationBuilder = NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.ic_stat_alert_notification)
                .setContentTitle("ALERTA DE INCENDIO")
                .setContentText("Obtén la ubicación ingrensando a la app")
                .setContentIntent(createPendingIntent())
                .setAutoCancel(true)
                .setVibrate(longArrayOf(500, 1000, 500, 1000))
                .setSound(getSoundUri())
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setVisibility(VISIBILITY_PUBLIC)
        sendNotification(notificationBuilder)
        vibrate()
    }

    private fun vibrate() {
        val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        vibrator.vibrate(longArrayOf(500, 1000, 500, 1000), -1)
    }

    private fun sendNotification(notificationBuilder: NotificationCompat.Builder) {
        val notificationId = generateId()
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(notificationId, notificationBuilder.build())
    }

    private fun createPendingIntent(): PendingIntent {
        val notifyIntent = Intent(context, MainActivity::class.java)
        notifyIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        return PendingIntent.getActivity(context, 0, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT)
    }

    private fun getSoundUri(): Uri {
        return Uri.parse("android.resource://" + getPackageName() + "/raw/" + R.raw.alarm)
    }

    private fun getPackageName() = "tesis.untref.com.firealerts"

    private fun generateId(): Int = (random.nextDouble() * MULTIPLIER).toInt()

    companion object {
        const val MULTIPLIER = 1000000
    }
}