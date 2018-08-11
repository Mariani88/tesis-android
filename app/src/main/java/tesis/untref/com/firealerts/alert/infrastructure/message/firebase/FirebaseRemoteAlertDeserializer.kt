package tesis.untref.com.firealerts.alert.infrastructure.message.firebase

import org.json.JSONObject
import tesis.untref.com.firealerts.alert.model.*
import java.util.*

class FirebaseRemoteAlertDeserializer {

    fun deserialize(jsonMap: MutableMap<String, String>): Alert {
        val id = jsonMap[ID]!!.toLong()
        val date = jsonMap[DATE]!!.toLong()
        val coordinate = parseCoordinates(jsonMap)
        return Alert(id, coordinate, Date(date))
    }

    private fun parseCoordinates(jsonMap: MutableMap<String, String>): Coordinate {
        val jsonLatitude = JSONObject(jsonMap[LATITUDE])
        val jsonLongitude = JSONObject(jsonMap[LONGITUDE])
        val latitude = parseLatitude(jsonLatitude)
        val longitude = parseLongitude(jsonLongitude)
        return Coordinate(latitude, longitude)
    }

    private fun parseLongitude(jsonLongitude: JSONObject): Longitude {
        val degree = jsonLongitude[DEGREE] as Int
        val minute = jsonLongitude[MINUTE] as Int
        val second = jsonLongitude[SECOND] as Double
        val cardinalPoint = CardinalPoint.valueOf(jsonLongitude[CARDINAL_POINT] as String)

        return Longitude(degree, minute, second, cardinalPoint)
    }

    private fun parseLatitude(jsonLatitude: JSONObject): Latitude {
        val degree = jsonLatitude[DEGREE] as Int
        val minute = jsonLatitude[MINUTE] as Int
        val second = jsonLatitude[SECOND] as Double
        val cardinalPoint = CardinalPoint.valueOf(jsonLatitude[CARDINAL_POINT] as String)

        return Latitude(degree, minute, second, cardinalPoint)
    }

    companion object {
        private const val ID = "id"
        private const val DATE = "date"
        private const val LATITUDE = "latitude"
        private const val LONGITUDE = "longitude"
        private const val DEGREE = "degree"
        private const val MINUTE = "minute"
        private const val SECOND = "second"
        private const val CARDINAL_POINT = "cardinal_point"
    }
}