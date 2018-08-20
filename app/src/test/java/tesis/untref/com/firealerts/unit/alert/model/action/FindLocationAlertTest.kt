package tesis.untref.com.firealerts.unit.alert.model.action

import com.google.android.gms.maps.model.LatLng
import io.reactivex.Flowable
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import tesis.untref.com.firealerts.alert.model.Alert
import tesis.untref.com.firealerts.alert.model.AlertRepository
import tesis.untref.com.firealerts.alert.model.action.FindLocationAlert
import tesis.untref.com.firealerts.alert.model.service.CoordinatesAdapterService
import tesis.untref.com.firealerts.unit.builder.AlertBuilder.Companion.createAlert

class FindLocationAlertTest {

    private lateinit var findLocationAlert: FindLocationAlert
    private lateinit var alertRepository: AlertRepository
    private lateinit var alert: Alert
    private lateinit var alertLocationFlowable: Flowable<LatLng>
    private lateinit var coordinatesAdapterService: CoordinatesAdapterService

    @Test
    fun findLocationAlertReturnAlertLocation(){
        givenAFindAlertAction()
        givenAnAlert()

        whenFindAlert()

        thenAlertLocationIsReturned()
    }

    private fun thenAlertLocationIsReturned() {
        assertThat(alertLocationFlowable.test().valueCount()).isEqualTo(1)
        alertLocationFlowable.test().assertValue { expectedLocation(it) }
    }

    private fun expectedLocation(alertLocation: LatLng)=
        alertLocation == coordinatesAdapterService.toDecimalDegreeCoordinate(alert.coordinate)

    private fun whenFindAlert() {
        alertLocationFlowable = findLocationAlert.find(alert.id)
    }

    private fun givenAnAlert() {
        alert = createAlert()
        alertRepository.addAll(listOf(alert)).subscribe()
    }

    private fun givenAFindAlertAction() {
        alertRepository = InMemoryAlertRepository()
        coordinatesAdapterService = CoordinatesAdapterService()
        findLocationAlert = FindLocationAlert(alertRepository, coordinatesAdapterService)
    }
}