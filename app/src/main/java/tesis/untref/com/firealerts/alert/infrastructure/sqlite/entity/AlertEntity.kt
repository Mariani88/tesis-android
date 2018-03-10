package tesis.untref.com.firealerts.alert.infrastructure.sqlite.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import tesis.untref.com.firealerts.alert.model.Alert
import tesis.untref.com.firealerts.alert.model.AlertAddress
import tesis.untref.com.firealerts.alert.model.Coordinate
import java.util.*

@Entity(tableName = "alerts")
data class AlertEntity(

        @PrimaryKey
        var id: Long? = null,

        @Embedded
        var coordinate: CoordinateEntity?,

        @ColumnInfo(name = "date")
        var date: Date? = null,

        @Embedded
        var alertAddressEntity: AlertAddressEntity?
) {

    constructor(alert: Alert) : this(alert.id, CoordinateEntity(LatitudeEntity(alert.coordinate.latitude),
            LongitudeEntity(alert.coordinate.longitude)), alert.date, AlertAddressEntity(alert.alertAddress))

    constructor() : this(null, null, null, null)

    fun toAlert(): Alert = Alert(id!!, Coordinate(coordinate!!.latitude!!.toLatitude(),
            coordinate!!.longitude!!.toLongitude()), date!!, alertAddressEntity!!.toAddress())
}