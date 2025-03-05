package com.buzzware.zabellauser.ui.activities

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.buzzware.zabellauser.R
import com.buzzware.zabellauser.databinding.ActivityYourRideBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class YourRideActivity : AppCompatActivity(), OnMapReadyCallback {

    private val binding: ActivityYourRideBinding by lazy {
        ActivityYourRideBinding.inflate(layoutInflater)
    }
    private var rideID: String? = ""
    private var riderUID: String? = ""
    //private val db = Firebase.firestore
    private lateinit var mMap: GoogleMap

    private var pickUpAddress = ""
    private var myGoogleMap: GoogleMap? = null
    private var pickUpLat = 0.0
    private var pickUpLng = 0.0
    private var price = 0.0
    private var dropOffAddress = ""
    private var driverPhoneNumber = ""
    private var driverId: String? = ""
    private var driverName = "--"
    private var driverVehicle: String? = "--"
    private var driverRating = "0"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val mapFragment =
            supportFragmentManager.findFragmentById(R.id.map_fragment) as? SupportMapFragment
        mapFragment?.getMapAsync(this)

        binding.apply {

           /* pickUpAddress = intent.getStringExtra("pickUpAddress")!!
            dropOffAddress = intent.getStringExtra("dropOffAddress")!!
            pickUpLat = intent.getDoubleExtra("pickUpLat", 0.0)
            pickUpLng = intent.getDoubleExtra("pickUpLng", 0.0)
            rideID = intent.getStringExtra("rideID")
            driverVehicle = intent.getStringExtra("carType")*/

            pickUpTv.text = pickUpAddress
            dropOffTv.text = dropOffAddress

            //getOrders()

            markCompleteBtn.setOnClickListener {
                //updateStatus(rideID!!, "dispute")
                startActivity(Intent(this@YourRideActivity,YourDestinationActivity::class.java))
                overridePendingTransition(
                    androidx.appcompat.R.anim.abc_fade_in,
                    androidx.appcompat.R.anim.abc_fade_out
                )
            }

            msgBtn.setOnClickListener {
                //startMessage()
            }

            phoneBtn.setOnClickListener {
                val dialIntent = Intent(Intent.ACTION_DIAL)
                dialIntent.data = Uri.parse("tel:$driverPhoneNumber")
                if (driverPhoneNumber.isNotEmpty()) {
                    startActivity(dialIntent)
                } else {
                    Toast.makeText(
                        this@YourRideActivity,
                        "Phone Number Not Available",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            backBtn.setOnClickListener { onBackPressed() }
        }
    }


    private fun setIcon(context: Activity, drawableID: Int): BitmapDescriptor {

        val drawable = ActivityCompat.getDrawable(context, drawableID)
        drawable!!.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
        val bitmap = Bitmap.createBitmap(
            drawable.intrinsicWidth,
            drawable.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        drawable.draw(canvas)
        return BitmapDescriptorFactory.fromBitmap(bitmap)
    }

    override fun onMapReady(googelMaps: GoogleMap) {
        myGoogleMap = googelMaps
        //mMap = googelMaps
        val latLng = LatLng(pickUpLat, pickUpLng)

        myGoogleMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16f))
       /* myGoogleMap?.addMarker(
            MarkerOptions().icon(setIcon(this, R.drawable.ic_car)).position(latLng)
        )*/
        /* myGoogleMap?.setOnMapClickListener { latLng ->
             myGoogleMap?.clear()
             myGoogleMap?.addMarker(MarkerOptions().icon(setIcon(this, R.drawable.ic_car)).position(latLng).title("Selected Location"))
         }*/
        myGoogleMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f))

        //mMap = googleMaps
        /*  if (ActivityCompat.checkSelfPermission(
                  this,
                  Manifest.permission.ACCESS_FINE_LOCATION
              ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                  this,
                  Manifest.permission.ACCESS_COARSE_LOCATION
              ) != PackageManager.PERMISSION_GRANTED
          ) {
              return
          }
          mMap.isMyLocationEnabled = true
          val latLng = LatLng(pickUpLat!!, pickUpLng!!)
          val zoomLevel = 15f
          mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoomLevel))
          mMap.addMarker(MarkerOptions().icon(setIcon(this, R.drawable.ic_mark)).position(latLng))*/
    }

    private fun updateNotification(title: String, msg: String, status: String) {
       /* binding.progressBar.visibility = View.VISIBLE
        val isRead = hashMapOf(
            Firebase.auth.currentUser!!.uid to false,
            driverId to false,
        )
        val notification = hashMapOf(
            "driverId" to driverId,
            "message" to msg,
            "orderId" to rideID,
            "isRead" to isRead,
            "timestamp" to System.currentTimeMillis(),
            "title" to title,
            "type" to status,
            "userId" to Firebase.auth.currentUser!!.uid,
        )
        db.collection("Notification").document().set(notification).addOnSuccessListener {
            binding.progressBar.visibility = View.GONE
            finish()
        }.addOnFailureListener {
            binding.progressBar.visibility = View.GONE
            Toast.makeText(this, it.message.toString(), Toast.LENGTH_SHORT).show()
        }*/
    }

    private fun formatDateTime(millis: Long): String {
        val formatter = SimpleDateFormat("MMMM dd, yyyy 'at' hh:mm a", Locale.ENGLISH)
        val date = Date(millis)
        return formatter.format(date)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(
            androidx.appcompat.R.anim.abc_fade_in,
            androidx.appcompat.R.anim.abc_fade_out
        )
    }
}