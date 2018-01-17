package tesis.untref.com.firealerts.infrastructure.sqlite.entity

import android.arch.persistence.room.ColumnInfo


class CoordinateEntity (
        private var degree: Int,
        private var minute: Int,
        private var second: Float,

        @ColumnInfo(name = "cardinal_point")
        private var cardinalPoint: String
){
}