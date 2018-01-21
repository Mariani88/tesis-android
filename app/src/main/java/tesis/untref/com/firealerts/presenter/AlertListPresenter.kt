package tesis.untref.com.firealerts.presenter

import tesis.untref.com.firealerts.view.AlertListActivity

class AlertListPresenter(private val alertListActivity: AlertListActivity) {

    fun showAlerts() {
        val lat = -34.55439
        val long = -58.60905809999997
        val alerts = listOf("Lat: $lat \nLong: $long ")
        alertListActivity.showAlerts(alerts)
    }
}