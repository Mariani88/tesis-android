package tesis.untref.com.firealerts.alert.view

import tesis.untref.com.firealerts.alert.presenter.reducedModel.AlertAddressReducedDataModel

interface AlertListView {
    fun showAlerts(alerts: List<AlertAddressReducedDataModel>)
    fun goGoogleMapsView(latitude: Double, longitude: Double)
}