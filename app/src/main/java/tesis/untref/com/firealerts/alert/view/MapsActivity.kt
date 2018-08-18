package tesis.untref.com.firealerts.alert.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import tesis.untref.com.firealerts.R
import tesis.untref.com.firealerts.alert.presenter.MapsActivityPresenter

class MapsActivity : AppCompatActivity(), OnMapReadyCallback, MapView {

    private lateinit var mMap: GoogleMap
    private lateinit var mapPresenter: MapsActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        mapPresenter = MapsActivityPresenter(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        val longitude = intent.getDoubleExtra("LONG", 0.0)
        val latitude = intent.getDoubleExtra("LAT", 0.0)
        mMap = googleMap
        mapPresenter.showLocationOnGoogleMaps(longitude, latitude)
    }

    override fun showOnGoogleMaps(latitude: Double, longitude: Double){
        val alertLocation = LatLng(latitude, longitude)
        mMap.addMarker(MarkerOptions().position(alertLocation).title("Marker in alert location"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(alertLocation, 14f))
    }
}