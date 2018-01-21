package tesis.untref.com.firealerts.presenter

import android.arch.persistence.room.Room
import tesis.untref.com.firealerts.infrastructure.sqlite.dao.AlertDataBase
import tesis.untref.com.firealerts.infrastructure.sqlite.repository.SQLiteAlertRepository
import tesis.untref.com.firealerts.model.Alert
import tesis.untref.com.firealerts.model.CoordinatesAdapterService
import tesis.untref.com.firealerts.model.interactor.FindAlertInteractor
import tesis.untref.com.firealerts.view.AlertListActivity

class AlertListPresenter(private val alertListActivity: AlertListActivity) {

    private val findAlertInteractor: FindAlertInteractor
    private val coordinatesAdapterService: CoordinatesAdapterService

    init {
        val alertDao = Room.databaseBuilder(alertListActivity, AlertDataBase::class.java, "database-name").build().alertDao()
        findAlertInteractor = FindAlertInteractor(SQLiteAlertRepository(alertDao))
        coordinatesAdapterService = CoordinatesAdapterService()
    }

    fun showAlerts() {
        //findAlertInteractor.findAlerts().subscribe { alertListActivity.showAlerts(it) }


        val alerts = alerts()
        alertListActivity.showAlerts(alerts)
    }

    private fun alerts(): List<String> {
        val lat = -34.55439
        val long = -58.60905809999997
        return listOf("Lat: $lat \nLong: $long ", "Lat: 34\nLong: 33")
    }

    fun showAlert(alertId: Long) {
        val lat = -34.55439
        val long = -58.60905809999997

        /*findAlertInteractor
                .find(alertId)
                .subscribe({refreshView(it)})*/

        alertListActivity.goGoogleMapsView(lat , long )
    }

    private fun refreshView(alert: Alert) {
        val googleMapsCoordinate = coordinatesAdapterService.toGoogleMapsCoordinate(alert.coordinate)
        alertListActivity.goGoogleMapsView(googleMapsCoordinate.latitude, googleMapsCoordinate.longitude)
    }
}