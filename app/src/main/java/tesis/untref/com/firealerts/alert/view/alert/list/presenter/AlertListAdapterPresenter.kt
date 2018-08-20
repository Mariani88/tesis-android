package tesis.untref.com.firealerts.alert.view.alert.list.presenter

import com.google.android.gms.maps.model.LatLng
import io.reactivex.android.schedulers.AndroidSchedulers
import tesis.untref.com.firealerts.alert.model.action.FindLocationAlert
import tesis.untref.com.firealerts.alert.view.AlertListView

class AlertListAdapterPresenter(private val findLocationAlert: FindLocationAlert,
                                private val alertListView: AlertListView) {

    fun findAlertLocationById(alertId: Long) {
        findLocationAlert
                .find(alertId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { refreshView(it) }
    }

    private fun refreshView(googleMapsCoordinate: LatLng) {
        alertListView.goGoogleMapsView(googleMapsCoordinate.latitude, googleMapsCoordinate.longitude)
    }
}