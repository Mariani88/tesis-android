package tesis.untref.com.firealerts.view.model

class CoordinatesAdapterService {
    fun toGoogleMapsCoordinate(coordinate: Coordinate): Double {
        return coordinate.degree.toDouble() + (coordinate.minute.toDouble()/60) + (coordinate.second.toDouble() / 3600)
    }
}