package tesis.untref.com.firealerts.model

import junit.framework.Assert
import org.junit.Before
import org.junit.Test
import tesis.untref.com.firealerts.view.model.CardinalPoint
import tesis.untref.com.firealerts.view.model.Coordinate
import tesis.untref.com.firealerts.view.model.CoordinatesAdapterService

class CoordinatesAdapterServiceTest {

    private lateinit var coordinate: Coordinate
    private val zero = 0
    private lateinit var coordinatesAdapterService: CoordinatesAdapterService
    private var googleMapsCoordinate: Double? = null

    @Before
    fun setUp() {
        coordinatesAdapterService = CoordinatesAdapterService()
    }

    @Test
    fun adaptOriginCoordinateToGoogleMapsCoordinatesShouldBeEqualsZero(){
        givenACoordinate()

        whenAdaptToGoogleMapCoordinate()

        thenAdaptWithValue(0.toDouble())
    }



    private fun thenAdaptWithValue(expectedGoogleMapsCoordinate: Double) {
        Assert.assertNotNull(googleMapsCoordinate)
        Assert.assertEquals(expectedGoogleMapsCoordinate, googleMapsCoordinate)
    }

    private fun whenAdaptToGoogleMapCoordinate() {
        googleMapsCoordinate = coordinatesAdapterService.toGoogleMapsCoordinate(coordinate)
    }

    private fun givenACoordinate( degree: Int = zero, minute: Int = zero, second: Float = zero.toFloat(), cardinalPoint: CardinalPoint = CardinalPoint.NORTH) {
        coordinate = Coordinate(degree, minute, second, cardinalPoint)
    }
}