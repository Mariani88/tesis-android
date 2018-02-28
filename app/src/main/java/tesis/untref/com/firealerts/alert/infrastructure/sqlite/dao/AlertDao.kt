package tesis.untref.com.firealerts.alert.infrastructure.sqlite.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import io.reactivex.Flowable
import tesis.untref.com.firealerts.alert.infrastructure.sqlite.entity.AlertEntity

@Dao
interface AlertDao {

    @Insert
    fun insertAll(vararg alertEntities: AlertEntity)

    @Query("SELECT * FROM alerts WHERE ID = :arg0")
    fun findById(alertId: Long): Flowable<AlertEntity>

    @Query("SELECT * FROM alerts")
    fun findAll(): Flowable<List<AlertEntity>>

    @Query("DELETE FROM alerts")
    fun removeAll()

    @Query("SELECT * FROM alerts ORDER BY date DESC")
    fun findAllSortedByDate(): Flowable<List<AlertEntity>>
}