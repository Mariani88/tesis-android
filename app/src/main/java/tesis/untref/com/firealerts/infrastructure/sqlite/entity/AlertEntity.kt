package tesis.untref.com.firealerts.infrastructure.sqlite.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import tesis.untref.com.firealerts.model.Alert
import java.util.*

@Entity(tableName = "alerts")
class AlertEntity (

        @PrimaryKey(autoGenerate = true)
        var id: Long? = null,

        @Embedded
        var latitude: LatitudeEntity? = null,

        @Embedded
        var longitude: LongitudeEntity? = null,

        @ColumnInfo(name = "date")
        var date: Long? = null
) {

    constructor(alert: Alert): this(alert.id, LatitudeEntity(alert.latitude), LongitudeEntity(alert.longitude), alert.date.time)

    fun toAlert(): Alert = Alert(id!!, latitude!!.toCoordinate(),  longitude!!.toCoordinate(), Date(date!!))

}