package tesis.untref.com.firealerts.alert.model

import io.reactivex.Completable
import io.reactivex.Flowable

interface AlertRepository {

    fun findById(alertId: Long): Flowable<Alert>

    fun addAll(alerts: List<Alert>): Completable

    fun findAll(): Flowable<List<Alert>>
    fun findAllSortedByDate(): Flowable<List<Alert>>
    fun removeAll(): Completable
}