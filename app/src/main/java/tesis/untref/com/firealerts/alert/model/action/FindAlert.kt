package tesis.untref.com.firealerts.alert.model.action

import io.reactivex.Flowable
import tesis.untref.com.firealerts.alert.model.Alert
import tesis.untref.com.firealerts.alert.model.AlertRepository

class FindAlert(private val alertRepository: AlertRepository) {

    fun find(alertId: Long): Flowable<Alert> = alertRepository.findById(alertId)

    fun findAlerts(): Flowable<List<Alert>> = alertRepository.findAllSortedByDate()
}