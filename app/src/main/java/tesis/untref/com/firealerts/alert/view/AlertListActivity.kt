package tesis.untref.com.firealerts.alert.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import tesis.untref.com.firealerts.R
import tesis.untref.com.firealerts.alert.infrastructure.sqlite.repository.AlertRepositoryProvider
import tesis.untref.com.firealerts.alert.model.action.DeleteAlerts
import tesis.untref.com.firealerts.alert.model.action.FindLocationAlert
import tesis.untref.com.firealerts.alert.model.action.FindAlertsSortedByDate
import tesis.untref.com.firealerts.alert.model.service.CoordinatesAdapterService
import tesis.untref.com.firealerts.alert.presenter.AlertListPresenter
import tesis.untref.com.firealerts.alert.presenter.reducedModel.AlertAddressReducedDataModel
import tesis.untref.com.firealerts.alert.view.alert.list.adapter.AlertListAdapter
import tesis.untref.com.firealerts.alert.view.alert.list.presenter.AlertListAdapterPresenter

class AlertListActivity : Activity(), AlertListView {

    private lateinit var alertListPresenter: AlertListPresenter
    private lateinit var alertListAdapterPresenter: AlertListAdapterPresenter
    private var alertIdsShowing = listOf<Long>()
    private lateinit var deleteButton: Button
    private lateinit var emptyAlertsTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alert_list)
        createPresenter()
        alertListPresenter.showAlerts()
        deleteButton = findViewById(R.id.delete_all_button)
        emptyAlertsTextView = findViewById(R.id.empty_alerts_text_view)
        deleteButton.setOnClickListener{alertListPresenter.removeAll()}
    }

    override fun showAlerts(alerts: List<AlertAddressReducedDataModel>){
        val alertAddresses = alerts.map { it.alertAddress }
        alertIdsShowing = alerts.map { it.alertId }
        val adapter = AlertListAdapter(alertAddresses, this, alertListAdapterPresenter, alertIdsShowing)
        val alertsList = findViewById<ListView>(R.id.listView)
        alertsList.adapter = adapter
        emptyAlertsTextView.visibility = if(alertIdsShowing.isEmpty()) VISIBLE else INVISIBLE
    }

    override fun goGoogleMapsView(latitude: Double, longitude: Double){
        val intent = Intent(this, MapsActivity::class.java)
        intent.putExtra("LAT", latitude)
        intent.putExtra("LONG", longitude)

        startActivity(intent)
    }

    private fun createPresenter() {
        val alertRepository = AlertRepositoryProvider.getInstance(this)
        val findLocationAlert = FindLocationAlert(alertRepository, CoordinatesAdapterService())
        val findAlertsSortedByDate = FindAlertsSortedByDate(alertRepository)
        val deleteAlerts = DeleteAlerts(alertRepository)
        alertListAdapterPresenter = AlertListAdapterPresenter(findLocationAlert, this)
        alertListPresenter = AlertListPresenter(this, findAlertsSortedByDate, deleteAlerts)
    }
}