package tesis.untref.com.firealerts.infrastructure.sqlite.repository

import io.reactivex.Flowable
import tesis.untref.com.firealerts.infrastructure.sqlite.dao.AlertDao
import tesis.untref.com.firealerts.infrastructure.sqlite.entity.AlertEntity
import tesis.untref.com.firealerts.model.Alert

class SQLiteAlertRepository (private val alertDao: AlertDao /*context: Context*/){

    //private val alertDao = Room.databaseBuilder(context, AlertDataBase::class.java, "database-name").build().alertDao()

    fun findById(alertId: Long): Flowable<Alert> =
        alertDao.findById(alertId).map { it.toAlert() }

    fun addAll(alerts: List<Alert>) = alertDao.insertAll(* alerts.map { AlertEntity(it) }.toTypedArray())
}