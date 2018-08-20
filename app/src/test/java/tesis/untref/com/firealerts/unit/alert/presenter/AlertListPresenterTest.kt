package tesis.untref.com.firealerts.unit.alert.presenter

import io.reactivex.Completable
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*
import tesis.untref.com.firealerts.alert.model.action.DeleteAlerts
import tesis.untref.com.firealerts.alert.model.action.FindLocationAlert
import tesis.untref.com.firealerts.alert.model.action.FindAlertsSortedByDate
import tesis.untref.com.firealerts.alert.model.service.CoordinatesAdapterService
import tesis.untref.com.firealerts.alert.presenter.AlertListPresenter
import tesis.untref.com.firealerts.alert.view.AlertListView
import tesis.untref.com.firealerts.unit.rules.RxCustomSchedulersRule

class AlertListPresenterTest {

    private lateinit var alertListPresenter: AlertListPresenter
    private lateinit var alertListView: AlertListView
    private lateinit var findLocationAlert: FindLocationAlert
    private lateinit var findAlertsSortedByDate: FindAlertsSortedByDate
    private lateinit var deleteAlerts: DeleteAlerts
    private lateinit var coordinatesAdapterService: CoordinatesAdapterService

    @JvmField
    @Rule
    val rxCustomSchedulersRule = RxCustomSchedulersRule()

    @Test
    fun removeAllShouldCallRemoveActionAndUpdateView(){
        givenAPresenter()

        whenRemoveAll()

        verifyCallRemoveActionAndUpdateView()
    }

    private fun verifyCallRemoveActionAndUpdateView() {
        verify(deleteAlerts, times(1)).invoke()
        verify(alertListView, times(1)).showAlerts(emptyList())
    }

    private fun givenAPresenter() {
        alertListView = mock(AlertListView::class.java)
        findLocationAlert = mock(FindLocationAlert::class.java)
        findAlertsSortedByDate = mock(FindAlertsSortedByDate::class.java)
        deleteAlerts = mock(DeleteAlerts::class.java)
        `when`(deleteAlerts.invoke()).thenReturn(Completable.fromAction { })

        coordinatesAdapterService = mock(CoordinatesAdapterService::class.java)
        alertListPresenter = AlertListPresenter(alertListView, findAlertsSortedByDate, deleteAlerts)
    }

    private fun whenRemoveAll() {
        alertListPresenter.removeAll()
    }
}
