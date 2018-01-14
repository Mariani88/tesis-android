package tesis.untref.com.firealerts.view.model

class CoordinatesAdapterService {
    fun toGoogleMapsCoordinate(coordinate: Coordinate): Double {
        val googleCoordinates = coordinate.degree.toDouble() + (coordinate.minute.toDouble() / 60) + (coordinate.second.toDouble() / 3600)
        return if(coordinate.cardinalPoint == CardinalPoint.WEST || coordinate.cardinalPoint == CardinalPoint.SOUTH) -googleCoordinates else googleCoordinates
    }
}