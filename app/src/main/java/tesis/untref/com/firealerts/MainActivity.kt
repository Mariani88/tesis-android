package tesis.untref.com.firealerts

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import tesis.untref.com.firealerts.view.AlertListActivity

//https://antonioleiva.com/mvp-android/

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button = findViewById<Button>(R.id.button)
        val intent = Intent(  this,  AlertListActivity::class.java)
        button.setOnClickListener { startActivity(intent) }
    }
}