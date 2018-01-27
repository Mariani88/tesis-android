package tesis.untref.com.firealerts.infrastructure.sqlite.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import io.reactivex.Completable
import io.reactivex.Flowable
import tesis.untref.com.firealerts.infrastructure.sqlite.entity.AlertEntity

@Dao
interface AlertDao {

    @Insert
    fun insertAll(vararg alertEntities: AlertEntity)

    @Query("SELECT * FROM alerts WHERE id = :arg0")
    fun findById(alertId: Long): Flowable<AlertEntity>

    @Query("SELECT * FROM alerts")
    fun findAll(): Flowable<List<AlertEntity>>

    @Query("DELETE FROM alerts")
    fun removeAll()
}