package tesis.untref.com.firealerts.extensions

import android.location.Address
import android.os.Bundle
import tesis.untref.com.firealerts.alert.model.AlertAddress
import java.util.*

private const val unAvailable = "unavailable"

fun emptyAddress(): Address {
    val emptyAddress = Address(Locale.getDefault())
    emptyAddress.setAddressLine(0, unAvailable)
    emptyAddress.adminArea = unAvailable
    emptyAddress.countryCode = unAvailable
    emptyAddress.countryName = unAvailable
    emptyAddress.latitude = 0.0
    emptyAddress.longitude = 0.0
    emptyAddress.locality = unAvailable
    emptyAddress.featureName = unAvailable
    emptyAddress.url = unAvailable
    emptyAddress.extras = Bundle.EMPTY
    emptyAddress.phone = unAvailable
    emptyAddress.premises = unAvailable
    emptyAddress.subLocality = unAvailable
    emptyAddress.subAdminArea = unAvailable
    emptyAddress.thoroughfare = unAvailable
    emptyAddress.subThoroughfare = unAvailable
    return emptyAddress
}

fun Address.toAlertAddress(): AlertAddress =
        AlertAddress(thoroughfare, subThoroughfare, locality, adminArea, countryName)