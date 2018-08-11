package tesis.untref.com.firealerts.unit.alert.model.service

import android.location.Address
import android.location.Geocoder
import com.google.android.gms.maps.model.LatLng
import junit.framework.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import tesis.untref.com.firealerts.alert.model.service.LocationService
import tesis.untref.com.firealerts.extensions.emptyAddress
import java.io.IOException

class LocationServiceTest {

    private val latLng = LatLng(30.0, 30.0)
    private lateinit var locationService: LocationService
    private var addresses: MutableList<Address>? = null

    @Mock
    private lateinit var validAddress: Address

    @Mock
    private lateinit var geoCoder: Geocoder

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        Mockito.`when`(validAddress.getAddressLine(0)).thenReturn("valid address")
        locationService = LocationService(geoCoder)
    }

    @Test
    fun unavailableNetworkReturnEmptyAddress(){
        givenGeoCoderWithUnavailableNetwork()

        whenFindLocation()

        thenReturnEmptyAddress()
    }

    @Test
    fun addressesNotFoundReturnEmptyAddress(){
        givenGeoCoderWithEmptyAddressList()

        whenFindLocation()

        thenReturnEmptyAddress()
    }

    @Test
    fun ifGeocoderReturnNullThenReturnEmptyAddress(){
        givenGeoCoderReturnNullList()

        whenFindLocation()

        thenReturnEmptyAddress()
    }

    @Test
    fun ifGeocoderReturnValidAddressThenReturnAddress(){
        givenGeoCoderReturnValidAddress()

        whenFindLocation()

        thenReturnValidAddress()
    }

    private fun thenReturnValidAddress() {
        Assert.assertTrue(addresses!!.isNotEmpty())
        Assert.assertEquals(validAddress.getAddressLine(0), addresses!![0].getAddressLine(0))
    }

    private fun givenGeoCoderReturnValidAddress() {
        Mockito.`when`(geoCoder.getFromLocation(latLng.latitude, latLng.longitude, 1))
                .thenReturn(mutableListOf(validAddress))
    }

    private fun givenGeoCoderReturnNullList() {
        Mockito.`when`(geoCoder.getFromLocation(latLng.latitude, latLng.longitude, 1))
                .thenReturn(null)
    }

    private fun thenReturnEmptyAddress() {
        Assert.assertTrue(addresses!!.isNotEmpty())
        Assert.assertEquals(emptyAddress().getAddressLine(0), addresses!![0].getAddressLine(0))
    }

    private fun whenFindLocation() {
        addresses = locationService.obtainAddress(latLng).test().await().values()
    }

    private fun givenGeoCoderWithUnavailableNetwork() {
        Mockito.`when`(geoCoder.getFromLocation(latLng.latitude, latLng.longitude, 1))
                .thenThrow(IOException())
    }

    private fun givenGeoCoderWithEmptyAddressList() {
        Mockito.`when`(geoCoder.getFromLocation(latLng.latitude, latLng.longitude, 1))
                .thenReturn(mutableListOf())
    }
}