package com.buzzware.zabellauser.ui.activities

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.buzzware.zabellauser.R
import com.buzzware.zabellauser.databinding.ActivityBookingBinding
import com.buzzware.zabellauser.ui.adapters.RideHistoryAdapter
import com.buzzware.zabellauser.ui.adapters.ScheduleAdapter
import com.buzzware.zabellauser.ui.model.Booking

class BookingActivity : AppCompatActivity() {

    private val binding: ActivityBookingBinding by lazy {
        ActivityBookingBinding.inflate(layoutInflater)
    }

    //private val db = Firebase.firestore
    //private val auth = Firebase.auth

    private val completedRideList: ArrayList<Booking> = ArrayList()
    private val scheduleRideList: ArrayList<Booking> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setListener()
        //getCompletedRides()
        //getScheduleBooking()

        binding.backBtn.setOnClickListener {
            finish()
        }

    }

   /* private fun getCompletedRides() {
        binding.progressBar.visibility = View.VISIBLE
        db.collection("Bookings").addSnapshotListener { value, error ->

            if (error != null) {
                binding.progressBar.visibility = View.GONE
                Toast.makeText(this, error.message.toString(), Toast.LENGTH_SHORT).show()
                return@addSnapshotListener
            }

            binding.progressBar.visibility = View.GONE
            value?.forEach {
                val booking = it.toObject(Booking::class.java)
                if (booking.status.equals("rideCompleted") || booking.status.equals("rated")){
                    if(booking.userId.equals(Firebase.auth.currentUser!!.uid)){
                        completedRideList.add(booking)
                    }
                }
                setRunningAdapter()
            }
        }
    }

    private fun getScheduleBooking() {
        binding.progressBar.visibility = View.VISIBLE
        db.collection("ScheduledRides").addSnapshotListener { value, error ->

            if (error != null) {
                binding.progressBar.visibility = View.GONE
                Toast.makeText(this, error.message.toString(), Toast.LENGTH_SHORT).show()
                return@addSnapshotListener
            }

            binding.progressBar.visibility = View.GONE
            value?.forEach {
                val booking = it.toObject(Booking::class.java)
                if (booking.userId.equals(auth.currentUser!!.uid)) {
                    scheduleRideList.add(booking)
                }
            }
        }
    }*/

    private fun setListener() {


        binding.runningRide.setOnClickListener {
            binding.runningRide.setBackgroundResource(R.drawable.gradient_button)
            binding.completeRide.setBackgroundResource(0)

            setRunningAdapter()
        }

        binding.completeRide.setOnClickListener {
            binding.completeRide.setBackgroundResource(R.drawable.gradient_button)
            binding.runningRide.setBackgroundResource(0)

            setCompleteAdapter()
        }
    }

    private fun setRunningAdapter() {
        binding.rideRV.layoutManager = LinearLayoutManager(this@BookingActivity)
        binding.rideRV.adapter = RideHistoryAdapter(this@BookingActivity, completedRideList)
    }

    private fun setCompleteAdapter() {
        binding.rideRV.layoutManager = LinearLayoutManager(this@BookingActivity)
        binding.rideRV.adapter = ScheduleAdapter(this@BookingActivity, scheduleRideList)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(
            androidx.appcompat.R.anim.abc_fade_in,
            androidx.appcompat.R.anim.abc_fade_out
        )
    }
}