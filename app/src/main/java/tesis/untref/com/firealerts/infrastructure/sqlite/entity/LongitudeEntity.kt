package tesis.untref.com.firealerts.infrastructure.sqlite.entity

import android.arch.persistence.room.ColumnInfo
import tesis.untref.com.firealerts.model.CardinalPoint
import tesis.untref.com.firealerts.model.Coordinate


class LongitudeEntity(
        var degree1: Int? = null,
        var minute1: Int? = null,
        var second1: Float? = null,

        @ColumnInfo(name = "cardinal_point1")
        var cardinalPoint1: String? = null
) {
    constructor(coordinate: Coordinate) : this(coordinate.degree, coordinate.minute, coordinate.second, coordinate.cardinalPoint.name)

    constructor(): this(null, null, null,null)

    fun toCoordinate(): Coordinate = Coordinate(degree1!!, minute1!!, second1!!, CardinalPoint.valueOf(cardinalPoint1!!))
}