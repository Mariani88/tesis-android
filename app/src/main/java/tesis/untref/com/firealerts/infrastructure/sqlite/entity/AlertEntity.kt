package tesis.untref.com.firealerts.infrastructure.sqlite.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "alerts")
class AlertEntity (

        @PrimaryKey
        private var id: Long,

        @Embedded
        private var latitude: CoordinateEntity,

        @Embedded
        private var longitude: CoordinateEntity,

        @ColumnInfo(name = "instant_time")
        private var instantTime: Long
) {


}