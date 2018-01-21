package tesis.untref.com.firealerts.model.interactor

import io.reactivex.Flowable
import tesis.untref.com.firealerts.model.Alert
import tesis.untref.com.firealerts.model.AlertRepository

class FindAlertInteractor(private val alertRepository: AlertRepository) {

    fun find(alertId: Long): Flowable<Alert> = alertRepository.findById(alertId)

    fun findAlerts(): Flowable<List<Alert>> = alertRepository.findAll()
}