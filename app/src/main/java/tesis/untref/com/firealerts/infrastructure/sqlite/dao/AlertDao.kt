package tesis.untref.com.firealerts.infrastructure.sqlite.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import tesis.untref.com.firealerts.infrastructure.sqlite.entity.AlertEntity

@Dao
interface AlertDao {

    @Insert
    fun insertAll(vararg alertEntities: AlertEntity)

    @Query("SELECT * FROM alerts WHERE id = (: alertId)  ")
    fun findById(alertId: Long): AlertEntity
}