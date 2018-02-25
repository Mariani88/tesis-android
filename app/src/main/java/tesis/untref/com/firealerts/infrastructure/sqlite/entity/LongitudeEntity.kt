package tesis.untref.com.firealerts.infrastructure.sqlite.entity

import android.arch.persistence.room.ColumnInfo
import tesis.untref.com.firealerts.model.CardinalPoint
import tesis.untref.com.firealerts.model.Longitude

data class LongitudeEntity(

        @ColumnInfo(name = " long_degree")
        var degree: Int? = null,

        @ColumnInfo(name = "long_minute")
        var minute: Int? = null,

        @ColumnInfo(name= "long_second")
        var second: Double? = null,

        @ColumnInfo(name = "long_cardinal_point")
        var cardinalPoint: String? = null
) {
    constructor(longitude: Longitude) : this(longitude.degree, longitude.minute, longitude.second, longitude.cardinalPoint.name)

    constructor(): this(null, null, null,null)

    fun toLongitude(): Longitude = Longitude(degree!!, minute!!, second!!, CardinalPoint.valueOf(cardinalPoint!!))
}