package tesis.untref.com.firealerts.alert.presenter

import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import tesis.untref.com.firealerts.alert.infrastructure.sqlite.repository.AlertRepositoryProvider
import tesis.untref.com.firealerts.alert.model.*
import tesis.untref.com.firealerts.alert.model.interactor.FindAlertInteractor
import tesis.untref.com.firealerts.alert.model.service.CoordinatesAdapterService
import tesis.untref.com.firealerts.alert.view.AlertListActivity
import java.util.*

class AlertListPresenter(private val alertListActivity: AlertListActivity) {

    private val findAlertInteractor: FindAlertInteractor
    private val coordinatesAdapterService: CoordinatesAdapterService
    private var id = 1L
    private val alertRepository: AlertRepository

    init {
        alertRepository = AlertRepositoryProvider.getInstance(alertListActivity)
        findAlertInteractor = FindAlertInteractor(alertRepository)
        coordinatesAdapterService = CoordinatesAdapterService()
    }

    fun showAlerts(): Disposable =
        findAlertInteractor.findAlerts()
                .map { toGoogleMapsCoordinates(it) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe{alertListActivity.showAlerts(it)}

    private fun toGoogleMapsCoordinates(alerts: List<Alert>): List<String> =
        alerts
                .map { coordinatesAdapterService.toGoogleMapsCoordinate(it.coordinate) }
                .map { "Lat: ${it.latitude}\nLong:${it.longitude}" }

    fun showAlert(alertId: Long) {
        findAlertInteractor
                .find(alertId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({refreshView(it)})
    }

    private fun refreshView(alert: Alert) {
        val googleMapsCoordinate = coordinatesAdapterService.toGoogleMapsCoordinate(alert.coordinate)
        alertListActivity.goGoogleMapsView(googleMapsCoordinate.latitude, googleMapsCoordinate.longitude)
    }

    fun storeDataTest() {
        val latitude = Latitude(34, 33,15.8, CardinalPoint.SOUTH)
        val longitude = Longitude(58, 36, 32.61, CardinalPoint.WEST)

        Completable
                .fromAction{alertRepository.addAll(listOf(Alert(id, Coordinate(latitude, longitude), Date())))}
                .subscribeOn(Schedulers.newThread())
                .subscribe()
        showAlerts()
        id++
    }

    fun removeAll() {
        alertRepository.removeAll()
                .subscribeOn(Schedulers.newThread())
                .subscribe {  }
        showAlerts()
    }
}