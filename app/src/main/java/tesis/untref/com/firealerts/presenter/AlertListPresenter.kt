package tesis.untref.com.firealerts.presenter

import android.arch.persistence.room.Room
import tesis.untref.com.firealerts.infrastructure.sqlite.dao.AlertDao
import tesis.untref.com.firealerts.infrastructure.sqlite.dao.AlertDataBase
import tesis.untref.com.firealerts.infrastructure.sqlite.entity.AlertEntity
import tesis.untref.com.firealerts.infrastructure.sqlite.entity.CoordinateEntity
import tesis.untref.com.firealerts.infrastructure.sqlite.entity.LatitudeEntity
import tesis.untref.com.firealerts.infrastructure.sqlite.entity.LongitudeEntity
import tesis.untref.com.firealerts.infrastructure.sqlite.repository.SQLiteAlertRepository
import tesis.untref.com.firealerts.model.Alert
import tesis.untref.com.firealerts.model.CardinalPoint
import tesis.untref.com.firealerts.model.CoordinatesAdapterService
import tesis.untref.com.firealerts.model.interactor.FindAlertInteractor
import tesis.untref.com.firealerts.view.AlertListActivity
import java.util.*

class AlertListPresenter(private val alertListActivity: AlertListActivity) {

    private val findAlertInteractor: FindAlertInteractor
    private val coordinatesAdapterService: CoordinatesAdapterService
    private val alertDao: AlertDao

    init {
        alertDao = Room.databaseBuilder(alertListActivity, AlertDataBase::class.java, "database-name").build().alertDao()
        findAlertInteractor = FindAlertInteractor(SQLiteAlertRepository(alertDao))
        coordinatesAdapterService = CoordinatesAdapterService()
    }

    fun showAlerts() {
        findAlertInteractor.findAlerts()
                .map { toGoogleMapsCoordinates(it) }
                .subscribe{alertListActivity.showAlerts(it)}
    }

    private fun toGoogleMapsCoordinates(alerts: List<Alert>): List<String> =
        alerts
                .map { coordinatesAdapterService.toGoogleMapsCoordinate(it.coordinate) }
                .map { "Lat: ${it.latitude}\nLong:${it.longitude}" }

    private fun alerts(): List<String> {
        val lat = -34.55439
        val long = -58.60905809999997
        return listOf("Lat: $lat \nLong: $long ", "Lat: 34\nLong: 33")
    }

    fun showAlert(alertId: Long) {
        findAlertInteractor
                .find(alertId)
                .subscribe({refreshView(it)})
    }

    private fun refreshView(alert: Alert) {
        val googleMapsCoordinate = coordinatesAdapterService.toGoogleMapsCoordinate(alert.coordinate)
        alertListActivity.goGoogleMapsView(googleMapsCoordinate.latitude, googleMapsCoordinate.longitude)
    }

    fun storeDataTest() {
        val latitude = LatitudeEntity(34, 33,15.8f, CardinalPoint.SOUTH.name)
        val longitude = LongitudeEntity(58, 36, 32.61f, CardinalPoint.WEST.name)
        alertDao.insertAll(AlertEntity(1L, CoordinateEntity(latitude, longitude), Date()))
    }
}