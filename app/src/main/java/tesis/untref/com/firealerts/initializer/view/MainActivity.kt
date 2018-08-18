package tesis.untref.com.firealerts.initializer.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import tesis.untref.com.firealerts.R
import tesis.untref.com.firealerts.alert.infrastructure.message.firebase.FirebaseTopicService
import tesis.untref.com.firealerts.initializer.presenter.MainPresenter
import tesis.untref.com.firealerts.alert.view.AlertListActivity
import tesis.untref.com.firealerts.initializer.model.action.InitApplication

//https://antonioleiva.com/mvp-android/
class MainActivity : Activity(), MainView {

    private lateinit var mainPresenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainPresenter = MainPresenter(this, InitApplication(FirebaseTopicService()))
        mainPresenter.initAppConfig()
    }

    override fun nextView(){
        val intent = Intent(  this,  AlertListActivity::class.java)
        startActivity(intent)
        finish()
    }
}