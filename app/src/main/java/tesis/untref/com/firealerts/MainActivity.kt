package tesis.untref.com.firealerts

import android.app.Activity
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import tesis.untref.com.firealerts.alert.presenter.MainPresenter
import tesis.untref.com.firealerts.alert.view.AlertListActivity

//https://antonioleiva.com/mvp-android/

class MainActivity : Activity() {

    private lateinit var mainPresenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainPresenter = MainPresenter(this)
        mainPresenter.initAppConfig()
    }

    fun nextView(){
        val intent = Intent(  this,  AlertListActivity::class.java)
        startActivity(intent)
        finish()
    }
}