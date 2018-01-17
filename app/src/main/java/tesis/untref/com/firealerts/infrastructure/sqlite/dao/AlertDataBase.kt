package tesis.untref.com.firealerts.infrastructure.sqlite.dao

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import tesis.untref.com.firealerts.infrastructure.sqlite.entity.AlertEntity

@Database(entities = [(AlertEntity::class)], version = 1)
abstract class AlertDataBase: RoomDatabase() {

    abstract fun alertDao(): AlertDao
}