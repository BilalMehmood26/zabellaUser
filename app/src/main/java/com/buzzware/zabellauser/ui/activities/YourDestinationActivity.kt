package com.buzzware.zabellauser.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.buzzware.zabellauser.R
import com.buzzware.zabellauser.databinding.ActivityYourDestinationBinding
import com.buzzware.zabellauser.ui.model.PaymentMethodsResponse
import com.stripe.android.Stripe

class YourDestinationActivity : AppCompatActivity() {

    private val binding: ActivityYourDestinationBinding by lazy {
        ActivityYourDestinationBinding.inflate(layoutInflater)
    }

    private var driverName = "--"
    private var driverRating = "0"
    private var status = ""
    private var driverId: String? = ""
    private var bookingDate = ""
    private var price = ""
    private var tipPrice = 0.0
    private var carType: String = ""
    private var secrat = ""
    lateinit var stripe: Stripe

    private var cardList: ArrayList<PaymentMethodsResponse.PaymentMethod> = ArrayList()
    private var rideID: String? = ""
    private var riderUID: String? = ""
    //private val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        /*price = intent.getStringExtra("price")!!
        bookingDate = intent.getStringExtra("bookingDate")!!
        status = intent.getStringExtra("status")!!
        driverName = intent.getStringExtra("driverName")!!
        driverRating = intent.getStringExtra("driverRating")!!
        rideID = intent.getStringExtra("rideID")!!*/

        binding.apply {
            backBtn.setOnClickListener {
                onBackPressed()
            }

           // getOrders()
            //GooglePaymentsUtil.createPaymentsClient(this@YourDestinationActivity)
            stripe = Stripe(
                this@YourDestinationActivity,
                "pk_live_51PgBExCo08Oa4W8HRRlISwH7IOZRW42joDX0KpJRo7RK4tZhrz29Cout7tSsBEWCeODsr7IhT8jQGNiUrMIwwR0h00jZcUoUkr"
            )

            markCompleteBtn.setOnClickListener {
                //showPaymentDialog(carType)
                startActivity(Intent(this@YourDestinationActivity,DashboardActivity::class.java))
                overridePendingTransition(
                    androidx.appcompat.R.anim.abc_fade_in,
                    androidx.appcompat.R.anim.abc_fade_out
                )
                finish()
            }

            //getCardList(UserSession.user.stripeCustid!!)
           // createFirstStep(UserSession.user.stripeCustid!!)

        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this@YourDestinationActivity,DashboardActivity::class.java))
        overridePendingTransition(
            androidx.appcompat.R.anim.abc_fade_in,
            androidx.appcompat.R.anim.abc_fade_out
        )
    }
}