package tesis.untref.com.firealerts.view.model

class CoordinatesAdapterService {
    fun toGoogleMapsCoordinate(coordinate: Coordinate): Double {
        return coordinate.degree.toDouble()
    }
}