package tesis.untref.com.firealerts.infrastructure.sqlite.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import tesis.untref.com.firealerts.model.Alert
import java.util.*

@Entity(tableName = "alerts")
class AlertEntity (

        @PrimaryKey
        private var id: Long,

        @Embedded
        private var latitude: CoordinateEntity,

        @Embedded
        private var longitude: CoordinateEntity,

        @ColumnInfo(name = "date")
        private var date: Date
) {

    constructor(alert: Alert): this(alert.id, CoordinateEntity(alert.latitude), CoordinateEntity(alert.longitude), alert.date)

    fun toAlert(): Alert = Alert(id, latitude.toCoordinate(),  longitude.toCoordinate(), date)

}