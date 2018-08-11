package tesis.untref.com.firealerts.unit.alert.model.action

import io.reactivex.Completable
import io.reactivex.Flowable
import tesis.untref.com.firealerts.alert.model.Alert
import tesis.untref.com.firealerts.alert.model.AlertRepository

class InMemoryAlertRepository : AlertRepository {

    private val alerts = mutableMapOf<Long, Alert>()

    override fun findById(alertId: Long): Flowable<Alert> = Flowable.just(alerts[alertId])

    override fun addAll(alerts: List<Alert>): Completable =
            Completable.fromAction { alerts.forEach { this.alerts[it.id] = it } }

    override fun findAll(): Flowable<List<Alert>> =
        Flowable.just(alerts.map { it.value })

    override fun findAllSortedByDate(): Flowable<List<Alert>> =
        Flowable.just(alerts.map { it.value }.sortedByDescending { it.date })

    override fun removeAll(): Completable =
        Completable.fromAction { alerts.clear() }
}