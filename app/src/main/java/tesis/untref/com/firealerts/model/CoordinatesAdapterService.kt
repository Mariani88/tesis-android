package tesis.untref.com.firealerts.model

import com.google.android.gms.maps.model.LatLng

class CoordinatesAdapterService {

    fun toGoogleMapsCoordinate(coordinate: Coordinate): LatLng {
        val latitude = coordinate.latitude
        val longitude = coordinate.longitude
        val transformedLatitude = angularTransform(latitude.degree, latitude.minute, latitude.second, latitude.cardinalPoint)
        val transformedLongitude = angularTransform(longitude.degree, longitude.minute, longitude.second, longitude.cardinalPoint)
        return LatLng(transformedLatitude, transformedLongitude)
    }

    private fun angularTransform(degree: Int, minute: Int, second: Float, cardinalPoint: CardinalPoint): Double{
        val transformedDegree = transform(degree, 1.toDouble())
        val transformedMinute = transform(minute, 60.toDouble())
        val transformedSecond = transform(second, 3600.toDouble())
        val googleCoordinates: Double = transformedDegree + transformedMinute + transformedSecond
        return if (cardinalPoint == CardinalPoint.WEST || cardinalPoint == CardinalPoint.SOUTH) - googleCoordinates else googleCoordinates
    }

    private fun transform(value: Number, divisor: Double): Double = value.toDouble() / divisor
}