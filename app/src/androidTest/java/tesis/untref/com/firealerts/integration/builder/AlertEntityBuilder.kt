package tesis.untref.com.firealerts.integration.builder

import tesis.untref.com.firealerts.alert.infrastructure.sqlite.entity.*
import tesis.untref.com.firealerts.alert.model.CardinalPoint
import java.util.*

class AlertEntityBuilder {

    companion object {

        private const val defaultDegree = 30
        private const val defaultMinute = 40
        private const val defaultSecond = 23.0
        private val east = CardinalPoint.EAST.name
        private val north = CardinalPoint.NORTH.name
        private const val defaultAlertId = 3L
        private val date = Date()
        private const val defaultStreet = "street"
        private const val defaultSubThoroughfare = "1234"
        private const val defaultLocality = "locality"
        private const val defaultProvince = "province"
        private const val defaultCountry = "argentina"

        fun createAlertEntity(alertId: Long = defaultAlertId, date: Date = Companion.date): AlertEntity {
            val latitude = createLatitudeEntity()
            val longitude = createLongitudeEntity()
            return AlertEntity(alertId, CoordinateEntity(latitude, longitude), date, createAlertAddressEntity())
        }

        fun createLatitudeEntity(degree: Int = defaultDegree, minute: Int = defaultMinute,
                                 second: Double = defaultSecond, cardinalPoint: String = north) =
            LatitudeEntity(degree, minute, second, cardinalPoint)

        fun createLongitudeEntity(degree: Int = defaultDegree, minute: Int = defaultMinute,
                                  second: Double = defaultSecond, cardinalPoint: String = east) =
                LongitudeEntity(degree, minute, second, cardinalPoint)

        fun createAlertAddressEntity(): AlertAddressEntity =
            AlertAddressEntity(defaultStreet, defaultSubThoroughfare, defaultLocality, defaultProvince, defaultCountry)
    }
}