package tesis.untref.com.firealerts.unit.alert.model.action

import io.reactivex.Flowable
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

import tesis.untref.com.firealerts.alert.model.Alert
import tesis.untref.com.firealerts.alert.model.action.FindAlertsSortedByDate
import tesis.untref.com.firealerts.unit.builder.AlertBuilder.Companion.createAlert
import tesis.untref.com.firealerts.unit.extentions.toDate
import java.time.LocalDateTime

class FindAlertsSortedByDateTest {

    private val now = LocalDateTime.now()
    private val yesterday = now.minusDays(1)
    private val oneWeekAgo = now.minusWeeks(1)
    private lateinit var lastAlert: Alert
    private lateinit var secondAlert: Alert
    private lateinit var firstAlert: Alert
    private lateinit var alertRepository: InMemoryAlertRepository
    private lateinit var findAllAlerts: FindAlertsSortedByDate
    private lateinit var storedAlerts: Flowable<List<Alert>>

    @Test
    fun findAlertsReturnAllAlertsSortedByDate() {
        givenAnFindAlertsAction()
        givenAThreeAlerts()

        whenFindAllAlerts()

        thenReturnAllAlertsSortedByDateDesc()
    }

    private fun thenReturnAllAlertsSortedByDateDesc() {
        storedAlerts.test().assertValue {
            it[0] == lastAlert && it[1] == secondAlert && it[2] == firstAlert
        }
    }

    private fun givenAThreeAlerts() {
        lastAlert = createAlert(date = now.toDate(), id = 3)
        secondAlert = createAlert(date = yesterday.toDate(), id = 2)
        firstAlert = createAlert(date = oneWeekAgo.toDate(), id = 1)
        alertRepository.addAll(listOf(firstAlert, secondAlert, lastAlert)).subscribe {}
    }

    private fun whenFindAllAlerts() {
        storedAlerts = findAllAlerts.findAlerts()
    }

    private fun givenAnFindAlertsAction() {
        alertRepository = InMemoryAlertRepository()
        findAllAlerts = FindAlertsSortedByDate(alertRepository)
    }
}