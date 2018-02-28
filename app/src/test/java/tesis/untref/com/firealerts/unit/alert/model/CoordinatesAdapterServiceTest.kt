package tesis.untref.com.firealerts.unit.alert.model

import com.google.android.gms.maps.model.LatLng
import junit.framework.Assert
import org.junit.Before
import org.junit.Test
import tesis.untref.com.firealerts.alert.model.*
import tesis.untref.com.firealerts.alert.model.service.CoordinatesAdapterService

class CoordinatesAdapterServiceTest {

    private lateinit var coordinate: Coordinate
    private val zero = 0
    private lateinit var coordinatesAdapterService: CoordinatesAdapterService
    private var googleMapsCoordinate: LatLng? = null
    private val degree = 40
    private lateinit var latitude: Latitude
    private lateinit var longitude: Longitude
    private val origin = 0.0

    @Before
    fun setUp() {
        coordinatesAdapterService = CoordinatesAdapterService()
    }

    @Test
    fun adaptOriginCoordinateToGoogleMapsCoordinatesShouldBeEqualsZero() {
        givenALatitude()
        givenALongitude()
        givenACoordinate(latitude, longitude)

        whenAdaptToGoogleMapCoordinate()

        thenAdaptWithValue(origin, origin)
    }

    @Test
    fun adaptDegreeCoordinateToGoogleMapsCoordinatesShouldBeEqualsDegree() {
        givenALatitude(degree = degree)
        givenALongitude()
        givenACoordinate(latitude, longitude)

        whenAdaptToGoogleMapCoordinate()

        thenAdaptWithValue(degree.toDouble(), origin)
    }

    @Test
    fun adaptMinuteCoordinateToGoogleMapsCoordinatesShouldBeLowerThan1AndCorrect() {
        givenALatitude(minute = 30)
        givenALongitude()
        givenACoordinate(latitude, longitude)

        whenAdaptToGoogleMapCoordinate()

        thenAdaptWithValue(0.5, origin)
    }

    @Test
    fun adaptSecondCoordinateToGoogleMapsCoordinatesShouldDivideFor3600() {
        givenALatitude(second = 36.0)
        givenALongitude()
        givenACoordinate(latitude, longitude)

        whenAdaptToGoogleMapCoordinate()

        thenAdaptWithValue(0.01, origin)
    }

    @Test
    fun adaptCoordinateToGoogleMapsCoordinatesShouldSumAdaptedDegreeMinuteAndSecond() {
        givenALatitude(degree = degree, minute = 30, second = 36.0)
        givenALongitude()
        givenACoordinate(latitude, longitude)

        whenAdaptToGoogleMapCoordinate()

        thenAdaptWithValue(degree + 0.5 + 0.01, origin)
    }

    @Test
    fun adaptCoordinateWithWestCardinalPointToGoogleMapsCoordinatesReturnNegativeCoordinates() {
        givenALatitude()
        givenALongitude(degree = degree, minute = 30, second = 36.0, cardinalPoint = CardinalPoint.WEST)
        givenACoordinate(latitude, longitude)

        whenAdaptToGoogleMapCoordinate()

        thenAdaptWithValue(origin, -(degree + 0.5 + 0.01))
    }

    @Test
    fun adaptCoordinateWithSouthCardinalPointToGoogleMapsCoordinatesReturnNegativeCoordinates() {
        givenALatitude(degree = degree, minute = 30, second = 36.0, cardinalPoint = CardinalPoint.SOUTH)
        givenALongitude()
        givenACoordinate(latitude, longitude)

        whenAdaptToGoogleMapCoordinate()

        thenAdaptWithValue(-(degree + 0.5 + 0.01), 0.0)
    }

    @Test
    fun adaptCoordinatesWithLatitudeAndLongitudeReturnValidGoogleCoordinates(){
        givenALatitude(degree = degree, minute = 30, second = 36.0, cardinalPoint = CardinalPoint.SOUTH)
        givenALongitude(degree = degree, minute = 30, second = 36.0, cardinalPoint = CardinalPoint.WEST)
        givenACoordinate(latitude, longitude)

        whenAdaptToGoogleMapCoordinate()

        thenAdaptWithValue(-(degree + 0.5 + 0.01), -(degree + 0.5 + 0.01))
    }

    private fun thenAdaptWithValue(expectedGoogleMapsLatitude: Double, expectedGoogleMapsLongitude: Double) {
        Assert.assertNotNull(googleMapsCoordinate)
        Assert.assertEquals(expectedGoogleMapsLatitude, googleMapsCoordinate!!.latitude)
        Assert.assertEquals(expectedGoogleMapsLongitude, googleMapsCoordinate!!.longitude)
    }

    private fun whenAdaptToGoogleMapCoordinate() {
        googleMapsCoordinate = coordinatesAdapterService.toGoogleMapsCoordinate(coordinate)
    }

    private fun givenACoordinate(latitude: Latitude, longitude: Longitude) {
        coordinate = Coordinate(latitude, longitude)
    }

    private fun givenALatitude(degree: Int = zero, minute: Int = zero, second: Double = zero.toDouble(),
                               cardinalPoint: CardinalPoint = CardinalPoint.NORTH){
        latitude = Latitude(degree, minute, second, cardinalPoint)
    }

    private fun givenALongitude(degree: Int = zero, minute: Int = zero, second: Double = zero.toDouble(),
                                cardinalPoint: CardinalPoint = CardinalPoint.EAST){
        longitude = Longitude(degree, minute, second, cardinalPoint)
    }
}