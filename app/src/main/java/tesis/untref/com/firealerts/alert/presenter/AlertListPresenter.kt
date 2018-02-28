package tesis.untref.com.firealerts.alert.presenter

import android.arch.persistence.room.Room
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import tesis.untref.com.firealerts.alert.infrastructure.sqlite.dao.AlertDao
import tesis.untref.com.firealerts.alert.infrastructure.sqlite.dao.AlertDataBase
import tesis.untref.com.firealerts.alert.infrastructure.sqlite.entity.AlertEntity
import tesis.untref.com.firealerts.alert.infrastructure.sqlite.entity.CoordinateEntity
import tesis.untref.com.firealerts.alert.infrastructure.sqlite.entity.LatitudeEntity
import tesis.untref.com.firealerts.alert.infrastructure.sqlite.entity.LongitudeEntity
import tesis.untref.com.firealerts.alert.infrastructure.sqlite.repository.SQLiteAlertRepository
import tesis.untref.com.firealerts.alert.model.Alert
import tesis.untref.com.firealerts.alert.model.CardinalPoint
import tesis.untref.com.firealerts.alert.model.service.CoordinatesAdapterService
import tesis.untref.com.firealerts.alert.model.interactor.FindAlertInteractor
import tesis.untref.com.firealerts.alert.view.AlertListActivity
import java.util.*

class AlertListPresenter(private val alertListActivity: AlertListActivity) {

    private val findAlertInteractor: FindAlertInteractor
    private val coordinatesAdapterService: CoordinatesAdapterService
    private val alertDao: AlertDao
    private var id = 1L

    init {
        alertDao = Room.databaseBuilder(alertListActivity, AlertDataBase::class.java, "database-name").build().alertDao()
        findAlertInteractor = FindAlertInteractor(SQLiteAlertRepository(alertDao))
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
        val latitude = LatitudeEntity(34, 33,15.8, CardinalPoint.SOUTH.name)
        val longitude = LongitudeEntity(58, 36, 32.61, CardinalPoint.WEST.name)

        Completable
                .fromAction{alertDao.insertAll(AlertEntity(id, CoordinateEntity(latitude, longitude), Date()))}
                .subscribeOn(Schedulers.newThread())
                .subscribe()
        showAlerts()
        id++
    }

    fun removeAll() {
        Completable
                .fromAction{alertDao.removeAll()}
                .subscribeOn(Schedulers.newThread())
                .subscribe {  }
        showAlerts()
    }
}