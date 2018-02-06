package tesis.untref.com.firealerts.model.service

import android.content.Context
import android.location.Address
import com.google.android.gms.maps.model.LatLng
import android.location.Geocoder
import android.os.Bundle
import io.reactivex.Maybe
import io.reactivex.Single
import tesis.untref.com.firealerts.extensions.emptyAddress

import java.util.*

class LocationService (private val context: Context, private val geoCoder: Geocoder){

    fun obtainAddress(latLng: LatLng): Address {
        val geoCoder = Geocoder(context, Locale.getDefault())

        /*Maybe
                .just(geoCoder.getFromLocation(latLng.latitude, latLng.longitude, 1))
                .onErrorReturn { listOf(emptyAddress()) }
                .map { it[0] }
                .toSingle()
        */

        val addresses = geoCoder.getFromLocation(latLng.latitude, latLng.longitude, 1) // Here 1 represent max location result to returned, by documents it recommended 1 to 5

        //val address = addresses[0].getAddressLine(0) // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
        //val city = addresses[0].getLocality()
        //val state = addresses[0].getAdminArea()
        //val country = addresses[0].getCountryName()
        //val postalCode = addresses[0].getPostalCode()
        //val knownName = addresses[0].getFeatureName() // Only if available else return NULL
        return addresses[0]
    }
}