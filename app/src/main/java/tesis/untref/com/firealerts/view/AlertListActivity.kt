package tesis.untref.com.firealerts.view

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import tesis.untref.com.firealerts.R

class AlertListActivity : AppCompatActivity(), AdapterView.OnItemClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alert_list)
        val alerts = listOf("alert")
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, alerts)
        val alertsList = findViewById<ListView>(R.id.listView)
        alertsList.adapter = adapter
        alertsList.onItemClickListener = this
    }

    override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        val intent = Intent(this, MapsActivity::class.java)
        startActivity(intent)
    }


}
