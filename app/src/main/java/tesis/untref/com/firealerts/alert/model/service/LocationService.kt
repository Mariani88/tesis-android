package tesis.untref.com.firealerts.alert.model.service

import android.location.Address
import com.google.android.gms.maps.model.LatLng
import android.location.Geocoder
import android.util.Log
import io.reactivex.Maybe
import io.reactivex.Single
import tesis.untref.com.firealerts.extensions.emptyAddress

class LocationService(private val geoCoder: Geocoder) {

    companion object {
        val EMPTY_ADDRESS = mutableListOf(emptyAddress())
        const val MAX_RESULTS = 1
    }

    fun obtainAddress(latLng: LatLng): Single<Address> =
            Maybe
                    .just(getAddress(latLng))
                    .onErrorReturnItem(EMPTY_ADDRESS)
                    .defaultIfEmpty(EMPTY_ADDRESS)
                    .map { returnSpecificAddress(it) }
                    .toSingle()

    private fun getAddress(latLng: LatLng): MutableList<Address> = try {
        geoCoder.getFromLocation(latLng.latitude, latLng.longitude, MAX_RESULTS)
    }catch (e: Exception){
        Log.d("error on location", "error on get address. Check network")
        EMPTY_ADDRESS
    }

    private fun returnSpecificAddress(addresses: List<Address>): Address =
            if (addresses.isEmpty()) EMPTY_ADDRESS[0] else addresses[0]
}