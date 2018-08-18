package tesis.untref.com.firealerts.unit.alert.presenter

import io.reactivex.Completable
import io.reactivex.Scheduler
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Function
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*
import tesis.untref.com.firealerts.alert.model.action.DeleteAlerts
import tesis.untref.com.firealerts.alert.model.action.FindAlert
import tesis.untref.com.firealerts.alert.model.action.FindAlertsSortedByDate
import tesis.untref.com.firealerts.alert.model.service.CoordinatesAdapterService
import tesis.untref.com.firealerts.alert.presenter.AlertListPresenter
import tesis.untref.com.firealerts.alert.view.AlertListView
import tesis.untref.com.firealerts.unit.rules.RxCustomSchedulersRule

class AlertListPresenterTest {

    private lateinit var alertListPresenter: AlertListPresenter
    private lateinit var alertListView: AlertListView
    private lateinit var findAlert: FindAlert
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
        findAlert = mock(FindAlert::class.java)
        findAlertsSortedByDate = mock(FindAlertsSortedByDate::class.java)
        deleteAlerts = mock(DeleteAlerts::class.java)
        `when`(deleteAlerts.invoke()).thenReturn(Completable.fromAction { })

        coordinatesAdapterService = mock(CoordinatesAdapterService::class.java)
        alertListPresenter = AlertListPresenter(alertListView, findAlert, findAlertsSortedByDate, deleteAlerts, coordinatesAdapterService)
    }

    private fun whenRemoveAll() {
        alertListPresenter.removeAll()
    }
}
