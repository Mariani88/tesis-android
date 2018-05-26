package tesis.untref.com.firealerts.alert.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ListAdapter
import android.widget.TextView
import tesis.untref.com.firealerts.R
import tesis.untref.com.firealerts.alert.presenter.AlertListPresenter

class AlertListAdapter(private val alerts: List<String>, private val context: Context,
                       private val alertListPresenter: AlertListPresenter,
                       private val alertIdsShowing: List<Long>) : BaseAdapter(), ListAdapter {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view = convertView
        if (view == null) {
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.list_view_item, null)
        }

        val listItemText = view!!.findViewById<View>(R.id.list_view_item_text) as TextView
        listItemText.text = alerts[position]

        val locationButton = view.findViewById<View>(R.id.location_button) as Button
        locationButton.setOnClickListener({ alertListPresenter.showAlert(alertIdsShowing[position]) })

        return view
    }

    override fun getItem(position: Int): String = alerts[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getCount(): Int = alerts.size
}