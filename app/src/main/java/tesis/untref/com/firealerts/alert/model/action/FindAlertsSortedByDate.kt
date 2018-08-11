package tesis.untref.com.firealerts.alert.model.action

import io.reactivex.Flowable
import tesis.untref.com.firealerts.alert.model.Alert
import tesis.untref.com.firealerts.alert.model.AlertRepository

class FindAlertsSortedByDate(private val alertRepository: AlertRepository) {

    fun findAlerts(): Flowable<List<Alert>> = alertRepository.findAllSortedByDate()
}