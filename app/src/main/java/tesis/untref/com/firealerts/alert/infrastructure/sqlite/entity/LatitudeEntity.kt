package tesis.untref.com.firealerts.alert.infrastructure.sqlite.entity

import android.arch.persistence.room.ColumnInfo
import tesis.untref.com.firealerts.alert.model.CardinalPoint
import tesis.untref.com.firealerts.alert.model.Latitude

data class LatitudeEntity(

        @ColumnInfo(name = "lat_degree")
        var degree: Int? = null,

        @ColumnInfo(name = "lat_minute")
        var minute: Int? = null,

        @ColumnInfo(name = "lat_second")
        var second: Double? = null,

        @ColumnInfo(name = "lat_cardinal_point")
        var cardinalPoint: String? = null
) {
    constructor(latitude: Latitude) : this(latitude.degree, latitude.minute, latitude.second, latitude.cardinalPoint.name)

    constructor(): this(null, null, null,null)

    fun toLatitude(): Latitude = Latitude(degree!!, minute!!, second!!, CardinalPoint.valueOf(cardinalPoint!!))
}