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
import tesis.untref.com.firealerts.alert.presenter.AlertListPresenter
import tesis.untref.com.firealerts.alert.presenter.dto.AlertAddressReducedDataModel
import tesis.untref.com.firealerts.alert.view.adapter.AlertListAdapter

class AlertListActivity : Activity() {

    private lateinit var alertListPresenter: AlertListPresenter
    private var alertIdsShowing = listOf<Long>()
    private lateinit var deleteButton: Button
    private lateinit var emptyAlertsTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alert_list)
        alertListPresenter = AlertListPresenter(this)
        alertListPresenter.showAlerts()
        deleteButton = findViewById(R.id.delete_all_button)
        emptyAlertsTextView = findViewById(R.id.empty_alerts_text_view)
        deleteButton.setOnClickListener{alertListPresenter.removeAll()}
    }

    fun showAlerts(alerts: List<AlertAddressReducedDataModel>){
        val alertAddresses = alerts.map { it.alertAddress }
        alertIdsShowing = alerts.map { it.alertId }
        val adapter = AlertListAdapter(alertAddresses, this, alertListPresenter, alertIdsShowing)
        val alertsList = findViewById<ListView>(R.id.listView)
        alertsList.adapter = adapter
        emptyAlertsTextView.visibility = if(alertIdsShowing.isEmpty()) VISIBLE else INVISIBLE
    }

    fun goGoogleMapsView(latitude: Double, longitude: Double){
        val intent = Intent(this, MapsActivity::class.java)
        intent.putExtra("LAT", latitude)
        intent.putExtra("LONG", longitude)

        startActivity(intent)
    }
}