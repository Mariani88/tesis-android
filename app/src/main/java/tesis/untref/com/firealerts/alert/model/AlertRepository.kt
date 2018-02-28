package tesis.untref.com.firealerts.alert.model

import io.reactivex.Flowable

interface AlertRepository {

    fun findById(alertId: Long): Flowable<Alert>

    fun addAll(alerts: List<Alert>)

    fun findAll(): Flowable<List<Alert>>
    fun findAllSortedByDate(): Flowable<List<Alert>>
}