package tesis.untref.com.firealerts.extensions

import android.location.Address
import android.os.Bundle
import java.util.*

fun emptyAddress(): Address{
    val emptyAddress = Address(Locale.getDefault())
    emptyAddress.setAddressLine(0, "no network")
    emptyAddress.adminArea = "no network"
    emptyAddress.countryCode = "no network"
    emptyAddress.countryName = "no network"
    emptyAddress.latitude = 0.0
    emptyAddress.longitude = 0.0
    emptyAddress.locality = "no network"
    emptyAddress.featureName = "no network"
    emptyAddress.url = "no network"
    emptyAddress.extras = Bundle.EMPTY
    emptyAddress.phone = "no network"
    emptyAddress.premises = "no network"
    emptyAddress.subLocality = "no network"
    emptyAddress.subAdminArea = "no network"
    emptyAddress.thoroughfare = "no network"
    emptyAddress.subThoroughfare = "no network"
    return emptyAddress
}