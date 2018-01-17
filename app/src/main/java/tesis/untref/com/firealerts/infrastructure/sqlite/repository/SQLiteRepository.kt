package tesis.untref.com.firealerts.infrastructure.sqlite.repository

import android.arch.persistence.room.Room
import android.content.Context
import tesis.untref.com.firealerts.infrastructure.sqlite.dao.AlertDataBase
import tesis.untref.com.firealerts.infrastructure.sqlite.entity.AlertEntity
import tesis.untref.com.firealerts.model.Alert


class SQLiteAlertRepository (context: Context){

    private val alertDao = Room.databaseBuilder(context, AlertDataBase::class.java, "database-name").build().alertDao()

    fun findById(alertId: Long): Alert =
        alertDao.findById(alertId).toAlert()

    fun addAll(alerts: List<Alert>) = alertDao.insertAll(* alerts.map { AlertEntity(it) }.toTypedArray())
}