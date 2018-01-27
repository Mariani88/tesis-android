package tesis.untref.com.firealerts.view

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import tesis.untref.com.firealerts.R
import tesis.untref.com.firealerts.presenter.AlertListPresenter

class AlertListActivity : AppCompatActivity(), AdapterView.OnItemClickListener {

    private lateinit var alertListPresenter: AlertListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alert_list)
        alertListPresenter = AlertListPresenter(this)
        alertListPresenter.showAlerts()
        val dataTestButton = findViewById<Button>(R.id.button2)
        dataTestButton.setOnClickListener { alertListPresenter.storeDataTest() }
        val deleteButton = findViewById<Button>(R.id.delete_all_button)
        deleteButton.setOnClickListener{alertListPresenter.removeAll()}
    }

    fun showAlerts(alerts: List<String>){
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, alerts)
        val alertsList = findViewById<ListView>(R.id.listView)
        alertsList.adapter = adapter
        alertsList.onItemClickListener = this
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        alertListPresenter.showAlert(id)
    }

    fun goGoogleMapsView(latitude: Double, longitude: Double){
        val intent = Intent(this, MapsActivity::class.java)
        intent.putExtra("LAT", latitude)
        intent.putExtra("LONG", longitude)

        startActivity(intent)
    }
}