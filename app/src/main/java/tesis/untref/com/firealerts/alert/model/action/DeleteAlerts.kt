package tesis.untref.com.firealerts.alert.model.action

import tesis.untref.com.firealerts.alert.model.AlertRepository

class DeleteAlerts(private val alertRepository: AlertRepository) {

    operator fun invoke() = alertRepository.removeAll()
}