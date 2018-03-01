package tesis.untref.com.firealerts.message.infrastructure

import android.content.ContentValues.TAG
import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import tesis.untref.com.firealerts.alert.infrastructure.notification.AlertLocalNotificationService
import tesis.untref.com.firealerts.alert.infrastructure.sqlite.repository.AlertRepositoryProvider
import java.util.*

class DefaultFirebaseMessagingService : FirebaseMessagingService() {

    private val sqLiteAlertRepository = AlertRepositoryProvider.getInstance(this)
    private val notificationService = AlertLocalNotificationService(this, Random())

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        Log.d(TAG, "From: " + remoteMessage.from!!)
        Log.d(TAG, "Message data payload: " + remoteMessage.data)
        val alert = FirebaseRemoteAlertDeserializer().deserialize(remoteMessage.data)
        sqLiteAlertRepository.addAll(listOf(alert))
                .subscribe({ notificationService.sendNotification() }, { logErrorAlertProcessing(it) })
    }

    private fun logErrorAlertProcessing(exception: Throwable) {
        Log.d("error on alert", exception.message)
    }
}