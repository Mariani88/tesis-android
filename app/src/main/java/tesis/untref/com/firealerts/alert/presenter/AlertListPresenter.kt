package tesis.untref.com.firealerts.alert.presenter

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import tesis.untref.com.firealerts.alert.infrastructure.sqlite.repository.AlertRepositoryProvider
import tesis.untref.com.firealerts.alert.model.*
import tesis.untref.com.firealerts.alert.model.action.FindAlert
import tesis.untref.com.firealerts.alert.model.action.FindAlertsSortedByDate
import tesis.untref.com.firealerts.alert.model.service.CoordinatesAdapterService
import tesis.untref.com.firealerts.alert.presenter.dto.AlertAddressReducedDataModel
import tesis.untref.com.firealerts.alert.view.AlertListActivity

class AlertListPresenter(private val alertListActivity: AlertListActivity) {

    private val findAlert: FindAlert
    private val findAlertsSortedByDate: FindAlertsSortedByDate
    private val coordinatesAdapterService: CoordinatesAdapterService
    private val alertRepository: AlertRepository

    init {
        alertRepository = AlertRepositoryProvider.getInstance(alertListActivity)
        findAlert = FindAlert(alertRepository)
        findAlertsSortedByDate = FindAlertsSortedByDate(alertRepository)
        coordinatesAdapterService = CoordinatesAdapterService()
    }

    fun showAlerts(): Disposable =
            findAlertsSortedByDate.findAlerts()
                    .map { prepareAddressAlertToShow(it) }
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe { alertListActivity.showAlerts(it) }

    private fun prepareAddressAlertToShow(alerts: List<Alert>): List<AlertAddressReducedDataModel> =
            alerts.map { AlertAddressReducedDataModel(it.id, it.getAddressString()) }

    fun showAlert(alertId: Long) {
        findAlert
                .find(alertId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ refreshView(it) })
    }

    private fun refreshView(alert: Alert) {
        val googleMapsCoordinate = coordinatesAdapterService.toDecimalDegreeCoordinate(alert.coordinate)
        alertListActivity.goGoogleMapsView(googleMapsCoordinate.latitude, googleMapsCoordinate.longitude)
    }

    fun removeAll() {
        alertRepository.removeAll()
                .subscribeOn(Schedulers.newThread())
                .subscribe { }
        showAlerts()
    }
}