package tesis.untref.com.firealerts.unit.alert.model.action

import io.reactivex.Flowable
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import tesis.untref.com.firealerts.alert.model.Alert
import tesis.untref.com.firealerts.alert.model.AlertRepository
import tesis.untref.com.firealerts.alert.model.action.FindAlert
import tesis.untref.com.firealerts.unit.builder.AlertBuilder.Companion.createAlert

class FindAlertTest {

    private lateinit var findAlert: FindAlert
    private lateinit var alertRepository: AlertRepository
    private lateinit var alert: Alert
    private lateinit var alertFlowable: Flowable<Alert>

    @Test
    fun findAlertReturnAlert(){
        givenAFindAlertAction()
        givenAnAlert()

        whenFindAlert()

        thenAlertIsReturned()
    }

    private fun thenAlertIsReturned() {
        assertThat(alertFlowable.test().valueCount()).isEqualTo(1)
        alertFlowable.test().assertValue { it == alert }
    }

    private fun whenFindAlert() {
        alertFlowable = findAlert.find(alert.id)
    }

    private fun givenAnAlert() {
        alert = createAlert()
        alertRepository.addAll(listOf(alert)).subscribe()
    }

    private fun givenAFindAlertAction() {
        alertRepository = InMemoryAlertRepository()
        findAlert = FindAlert(alertRepository)
    }
}