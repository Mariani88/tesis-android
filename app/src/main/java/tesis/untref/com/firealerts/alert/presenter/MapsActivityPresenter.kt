package tesis.untref.com.firealerts.alert.presenter

import tesis.untref.com.firealerts.alert.view.MapView

class MapsActivityPresenter(private val mapView: MapView) {

    fun showLocationOnGoogleMaps(longitude: Double, latitude: Double) {
        mapView.showOnGoogleMaps(latitude, longitude)
    }
}