package tesis.untref.com.firealerts.integration.location

import android.location.Geocoder
import android.support.test.InstrumentationRegistry
import com.google.android.gms.maps.model.LatLng
import org.junit.Test
import tesis.untref.com.firealerts.alert.model.service.LocationService
import java.util.*


class LocationServiceTest {

    @Test
    fun fdfdfk() {
        val context = InstrumentationRegistry.getTargetContext()
        val geocoder = Geocoder(context, Locale.getDefault())
        val locationService = LocationService(context, geocoder)
        val address = locationService.obtainAddress(LatLng(-34.5539, -58.609))

    }


}