package tesis.untref.com.firealerts.unit.alert.model.infrastructure.message.firebase

import junit.framework.Assert
import org.junit.Before
import org.junit.Test
import tesis.untref.com.firealerts.alert.infrastructure.message.firebase.FirebaseRemoteAlertDeserializer
import tesis.untref.com.firealerts.alert.model.Alert
import tesis.untref.com.firealerts.alert.model.Latitude
import tesis.untref.com.firealerts.alert.model.Longitude
import tesis.untref.com.firealerts.unit.builder.AlertBuilder.Companion.createCoordinate
import java.util.*

class FirebaseRemoteAlertDeserializerTest {

    private lateinit var firebaseRemoteAlertDeserializer: FirebaseRemoteAlertDeserializer
    private var alert: Alert? = null
    private val id = 3L
    private val date = Date()
    private val coordinate = createCoordinate()
    private var jsonMap: MutableMap<String, String> = mutableMapOf()

    @Before
    fun setUp(){
        firebaseRemoteAlertDeserializer = FirebaseRemoteAlertDeserializer()
    }

    @Test
    fun dlfdkfjk(){
        givenAJsonMap()

        whenParseMessage()

        thenParseCorrectly()
    }

    private fun thenParseCorrectly() {
        Assert.assertNotNull(alert)
        Assert.assertEquals(id, alert?.id)
        Assert.assertEquals(date, alert?.date)
        Assert.assertEquals(coordinate, alert?.coordinate)
    }

    private fun whenParseMessage() {
        alert = firebaseRemoteAlertDeserializer.deserialize(jsonMap)
    }

    private fun givenAJsonMap() {
        jsonMap[ID] = id.toString()
        jsonMap[DATE] = date.time.toString()
        jsonMap[LATITUDE] = toJsonLatitude(coordinate.latitude)
        jsonMap[LONGITUDE] = toJsonLongitude(coordinate.longitude)
    }

    private fun toJsonLatitude(latitude: Latitude): String =
            "{\"$DEGREE\":${latitude.degree}, \"$MINUTE\":${latitude.minute}, " +
                "\"$SECOND\":${latitude.second}, \"$CARDINAL_POINT\":${latitude.cardinalPoint}}"


    private fun toJsonLongitude(longitude: Longitude): String  =
            "{\"$DEGREE\":${longitude.degree}, \"$MINUTE\":${longitude.minute}, " +
                "\"$SECOND\":${longitude.second}, \"$CARDINAL_POINT\":${longitude.cardinalPoint}}"


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