package tesis.untref.com.firealerts.message.infrastructure

import android.content.ContentValues.TAG
import android.location.Geocoder
import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import tesis.untref.com.firealerts.alert.infrastructure.notification.AlertLocalNotificationService
import tesis.untref.com.firealerts.alert.infrastructure.sqlite.repository.AlertRepositoryProvider
import tesis.untref.com.firealerts.alert.model.service.CoordinatesAdapterService
import tesis.untref.com.firealerts.alert.model.service.LocationService
import tesis.untref.com.firealerts.extensions.toAlertAddress
import java.util.*

class DefaultFirebaseMessagingService : FirebaseMessagingService() {

    private val sqLiteAlertRepository = AlertRepositoryProvider.getInstance(this)
    private val notificationService = AlertLocalNotificationService(this, Random())
    private lateinit var locationService: LocationService
    private val service = CoordinatesAdapterService()

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        Log.d(TAG, "From: " + remoteMessage.from!!)
        Log.d(TAG, "Message data payload: " + remoteMessage.data)
        locationService = LocationService(Geocoder(this, Locale.getDefault()))
        val alert = FirebaseRemoteAlertDeserializer().deserialize(remoteMessage.data)
        locationService.obtainAddress(service.toGoogleMapsCoordinate(alert.coordinate))
                .doOnSuccess { alert.addAddress(it.toAlertAddress()) }
                .flatMapCompletable { sqLiteAlertRepository.addAll(listOf(alert)) }
                .subscribe({ notificationService.sendNotification() }, { logErrorAlertProcessing(it) })
    }

    private fun logErrorAlertProcessing(exception: Throwable) {
        Log.d("error on alert", exception.message)
    }
}