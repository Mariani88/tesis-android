package tesis.untref.com.firealerts.infrastructure.sqlite.entity

import android.arch.persistence.room.ColumnInfo
import tesis.untref.com.firealerts.model.CardinalPoint
import tesis.untref.com.firealerts.model.Coordinate

class LongitudeEntity(

        @ColumnInfo(name = " long_degree")
        var degree: Int? = null,

        @ColumnInfo(name = "long_minute")
        var minute: Int? = null,

        @ColumnInfo(name= "long_second")
        var second: Float? = null,

        @ColumnInfo(name = "long_cardinal_point")
        var cardinalPoint: String? = null
) {
    constructor(coordinate: Coordinate) : this(coordinate.degree, coordinate.minute, coordinate.second, coordinate.cardinalPoint.name)

    constructor(): this(null, null, null,null)

    fun toCoordinate(): Coordinate = Coordinate(degree!!, minute!!, second!!, CardinalPoint.valueOf(cardinalPoint!!))
}