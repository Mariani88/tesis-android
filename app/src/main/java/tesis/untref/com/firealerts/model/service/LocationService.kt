package tesis.untref.com.firealerts.model.service

import android.location.Address
import com.google.android.gms.maps.model.LatLng
import android.location.Geocoder
import io.reactivex.Maybe
import io.reactivex.Single
import tesis.untref.com.firealerts.extensions.emptyAddress

class LocationService (private val geoCoder: Geocoder){

    fun obtainAddress(latLng: LatLng): Single<Address> =
        Maybe
                .just(geoCoder.getFromLocation(latLng.latitude, latLng.longitude, 1))
                .onErrorReturn { listOf(emptyAddress()) }
                .map { it[0] }
                .toSingle()
}