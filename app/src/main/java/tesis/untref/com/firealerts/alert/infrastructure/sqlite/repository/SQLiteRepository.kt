package tesis.untref.com.firealerts.alert.infrastructure.sqlite.repository

import io.reactivex.Completable
import io.reactivex.Flowable
import tesis.untref.com.firealerts.alert.infrastructure.sqlite.dao.AlertDao
import tesis.untref.com.firealerts.alert.infrastructure.sqlite.entity.AlertEntity
import tesis.untref.com.firealerts.alert.model.Alert
import tesis.untref.com.firealerts.alert.model.AlertRepository

class SQLiteAlertRepository (private val alertDao: AlertDao): AlertRepository{

    override fun findAll(): Flowable<List<Alert>> =
        alertDao.findAll().map { it.map { it.toAlert() } }

    override fun findById(alertId: Long): Flowable<Alert> =
        alertDao.findById(alertId).map { it.toAlert() }

    override fun addAll(alerts: List<Alert>) =
            alertDao.insertAll(*alerts.map { AlertEntity(it) }.toTypedArray())

    override fun findAllSortedByDate(): Flowable<List<Alert>> =
            alertDao.findAllSortedByDate().map { it.map { it.toAlert() } }

    override fun removeAll(): Completable =
        Completable
                .fromAction{alertDao.removeAll()}

}