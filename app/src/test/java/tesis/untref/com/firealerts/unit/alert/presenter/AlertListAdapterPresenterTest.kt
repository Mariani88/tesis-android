package tesis.untref.com.firealerts.unit.alert.presenter

import com.google.android.gms.maps.model.LatLng
import io.reactivex.Flowable
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*
import tesis.untref.com.firealerts.alert.model.action.FindLocationAlert
import tesis.untref.com.firealerts.alert.view.AlertListView
import tesis.untref.com.firealerts.alert.view.alert.list.presenter.AlertListAdapterPresenter
import tesis.untref.com.firealerts.unit.rules.RxCustomSchedulersRule

class AlertListAdapterPresenterTest {

    private lateinit var findLocationAlert: FindLocationAlert
    private lateinit var alertListView: AlertListView
    private lateinit var alertListAdapterPresenter: AlertListAdapterPresenter

    @JvmField
    @Rule
    val customSchedulersRule = RxCustomSchedulersRule()

    @Test
    fun findAlertLocationGoToGoogleMapsViewToShowIt(){
        givenAPresenter()

        whenFindAlertLocationById()

        thenGoToGoogleMapsViewWithAlertLocation()
    }

    private fun thenGoToGoogleMapsViewWithAlertLocation() {
        verify(alertListView, times(1)).goGoogleMapsView(LATITUDE, LONGITUDE)
    }

    private fun whenFindAlertLocationById() {
        alertListAdapterPresenter.findAlertLocationById(ALERT_ID)
    }

    private fun givenAPresenter() {
        findLocationAlert = mock(FindLocationAlert::class.java)
        `when`(findLocationAlert.find(ALERT_ID)).thenReturn(Flowable.just(alertLocation))
        alertListView = mock(AlertListView::class.java)
        alertListAdapterPresenter = AlertListAdapterPresenter(findLocationAlert, alertListView)
    }

    companion object {
        private const val ALERT_ID = 2932L
        private const val LATITUDE = 34.0
        private const val LONGITUDE = 45.0
        private val alertLocation = LatLng(LATITUDE, LONGITUDE)
    }
}