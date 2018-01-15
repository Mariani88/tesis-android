package tesis.untref.com.firealerts.view.model

class CoordinatesAdapterService {

    fun toGoogleMapsCoordinate(coordinate: Coordinate): Double {
        val transformedMinute = transform(coordinate.minute, 60.toDouble())
        val transformedSecond = transform(coordinate.second, 3600.toDouble())
        val transformedDegree = transform(coordinate.degree, 1.toDouble())
        val googleCoordinates: Double = transformedDegree + transformedMinute + transformedSecond
        return if (coordinate.isWest() || coordinate.isSouth()) - googleCoordinates else googleCoordinates
    }

    private fun transform(value: Number, divisor: Double): Double = value.toDouble() / divisor
}