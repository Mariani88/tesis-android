package tesis.untref.com.firealerts.alert.view

import android.content.Intent
import android.content.pm.ActivityInfo
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import tesis.untref.com.firealerts.R
import tesis.untref.com.firealerts.alert.presenter.AlertListPresenter
import tesis.untref.com.firealerts.alert.presenter.dto.AlertAddressDto

class AlertListActivity : AppCompatActivity(), AdapterView.OnItemClickListener {

    private lateinit var alertListPresenter: AlertListPresenter
    private var alertIdsShowing = listOf<Long>()
    private lateinit var deleteButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alert_list)
        alertListPresenter = AlertListPresenter(this)
        alertListPresenter.showAlerts()
        deleteButton = findViewById(R.id.delete_all_button)
        deleteButton.setOnClickListener{alertListPresenter.removeAll()}
    }

    fun showAlerts(alerts: List<AlertAddressDto>){
        val alertAddresses = alerts.map { it.alertAddress }
        alertIdsShowing = alerts.map { it.alertId }
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, alertAddresses)
        val alertsList = findViewById<ListView>(R.id.listView)
        alertsList.adapter = adapter
        alertsList.onItemClickListener = this
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        alertListPresenter.showAlert(alertIdsShowing[position] )
    }

    fun goGoogleMapsView(latitude: Double, longitude: Double){
        val intent = Intent(this, MapsActivity::class.java)
        intent.putExtra("LAT", latitude)
        intent.putExtra("LONG", longitude)

        startActivity(intent)
    }
}