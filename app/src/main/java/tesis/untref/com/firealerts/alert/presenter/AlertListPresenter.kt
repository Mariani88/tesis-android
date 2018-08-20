package tesis.untref.com.firealerts.alert.presenter

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import tesis.untref.com.firealerts.alert.model.Alert
import tesis.untref.com.firealerts.alert.model.action.DeleteAlerts
import tesis.untref.com.firealerts.alert.model.action.FindAlertsSortedByDate
import tesis.untref.com.firealerts.alert.presenter.reducedModel.AlertAddressReducedDataModel
import tesis.untref.com.firealerts.alert.view.AlertListView

open class AlertListPresenter(private val alertListView: AlertListView,
                              private val findAlertsSortedByDate: FindAlertsSortedByDate,
                              private val deleteAlerts: DeleteAlerts) {

    fun showAlerts(): Disposable =
            findAlertsSortedByDate.findAlerts()
                    .map { prepareAddressAlertToShow(it) }
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe { alertListView.showAlerts(it) }

    fun removeAll() {
        deleteAlerts()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { alertListView.showAlerts(emptyList()) }
    }

    private fun prepareAddressAlertToShow(alerts: List<Alert>): List<AlertAddressReducedDataModel> =
            alerts.map { AlertAddressReducedDataModel(it.id, it.getAddressString()) }
}