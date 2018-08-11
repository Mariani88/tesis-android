package tesis.untref.com.firealerts.unit.alert.model.action

import org.junit.Test

import tesis.untref.com.firealerts.alert.model.action.DeleteAlerts
import tesis.untref.com.firealerts.unit.builder.AlertBuilder.Companion.createAlert

class DeleteAlertsTest {

    private lateinit var alertRepository: InMemoryAlertRepository
    private lateinit var deleteAlerts: DeleteAlerts

    @Test
    fun deleteAlertsMustDeleteAll(){
        givenADeleteAlerts()
        givenAnAlert()

        whenDeleteAction()

        thenThereAreNotAlerts()
    }

    private fun givenAnAlert() {
        alertRepository.addAll(listOf(createAlert())).subscribe()
    }

    private fun thenThereAreNotAlerts() {
        alertRepository.findAll().test().assertValue { it.isEmpty() }
    }

    private fun whenDeleteAction() {
        deleteAlerts().subscribe()
    }

    private fun givenADeleteAlerts() {
        alertRepository = InMemoryAlertRepository()
        deleteAlerts = DeleteAlerts(alertRepository)
    }
}