package tesis.untref.com.firealerts.message.infrastructure

import android.app.NotificationManager
import android.content.ContentValues.TAG
import android.content.Context
import android.support.v4.app.NotificationCompat
import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import tesis.untref.com.firealerts.R
import tesis.untref.com.firealerts.alert.infrastructure.sqlite.repository.AlertRepositoryProvider

class DefaultFirebaseMessagingService: FirebaseMessagingService() {

    private val notificationId = 0
    private val sqLiteAlertRepository = AlertRepositoryProvider.getInstance(this)

    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
        Log.d(TAG, "From: " + remoteMessage!!.from!!)

        if (remoteMessage.data.isNotEmpty()) {
            Log.d(TAG, "Message data payload: " + remoteMessage.data)
            val alert = FirebaseRemoteAlertDeserializer().deserialize(remoteMessage.data)
            sqLiteAlertRepository.addAll(listOf(alert))
            sendNotification()
        }
    }

    private fun sendNotification() {
        val notificationBuilder = NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.notification_icon_background)
                .setContentTitle("My notification")
                .setContentText("Hello World!")
        notificationId.inc()
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(notificationId, notificationBuilder.build())
    }
}