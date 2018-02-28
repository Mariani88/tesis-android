package tesis.untref.com.firealerts.alert.infrastructure.sqlite.dao

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import tesis.untref.com.firealerts.alert.infrastructure.sqlite.converter.DateConverter
import tesis.untref.com.firealerts.alert.infrastructure.sqlite.entity.AlertEntity

@Database(entities = [(AlertEntity::class)], version = 1)
@TypeConverters(DateConverter::class)
abstract class AlertDataBase: RoomDatabase() {

    abstract fun alertDao(): AlertDao
}