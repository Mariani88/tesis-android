package tesis.untref.com.firealerts.unit.builder

import tesis.untref.com.firealerts.alert.model.*
import java.util.*

class AlertBuilder {

    companion object {

        private const val defaultDegree = 49
        private const val defaultMinute = 30
        private const val defaultSecond = 45.3
        private val defaultLatitudeCardinalPoint = CardinalPoint.SOUTH
        private val defaultLongitudeCardinalPoint = CardinalPoint.WEST
        private const val defaultId = 1L
        private val defaultDate = Date()

        fun createAlert(id: Long = defaultId, coordinate: Coordinate = createCoordinate(),
                        date: Date = defaultDate) = Alert(id, coordinate, date)

        fun createLatitude(degree: Int = defaultDegree, minute: Int = defaultMinute,
                           second: Double = defaultSecond,
                           cardinalPoint: CardinalPoint = defaultLatitudeCardinalPoint) =
                Latitude(degree, minute, second, cardinalPoint)

        fun createLongitude(degree: Int = defaultDegree, minute: Int = defaultMinute,
                            second: Double = defaultSecond,
                            cardinalPoint: CardinalPoint = defaultLongitudeCardinalPoint) =
                Longitude(degree, minute, second, cardinalPoint)

        fun createCoordinate(latitude: Latitude = createLatitude(),
                             longitude: Longitude = createLongitude()): Coordinate =
                Coordinate(latitude, longitude)
    }
}