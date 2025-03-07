package com.buzzware.zabellauser.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.buzzware.zabellauser.R
import com.buzzware.zabellauser.databinding.ActivityEditProfileBinding
import com.buzzware.zabellauser.databinding.ActivitySelectVehicleBinding
import com.buzzware.zabellauser.ui.adapters.SelectVehicleAdapter
import com.buzzware.zabellauser.ui.model.SelectCar

class SelectVehicleActivity : AppCompatActivity() {

    private val binding: ActivitySelectVehicleBinding by lazy {
        ActivitySelectVehicleBinding.inflate(layoutInflater)
    }

    private var selectCar : ArrayList<SelectCar> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        selectCar = arrayListOf(
            SelectCar(R.drawable.car_img_two,"SENDAN - Black Mercedes Benz S-class","2","2","$206.12"),
            SelectCar(R.drawable.car_img_one,"SUV - Black Cadillac Escalade","4","4","$206.12"),
            SelectCar(R.drawable.car_img_four,"Sprinter Van - Black Mercedes","6","6","$206.12")
        )
        binding.apply {

            backBtn.setOnClickListener{
                finish()
            }

            viewPager.adapter = SelectVehicleAdapter(selectCar){
                startActivity(Intent(this@SelectVehicleActivity,YourRideActivity::class.java))
                overridePendingTransition(
                    androidx.appcompat.R.anim.abc_fade_in,
                    androidx.appcompat.R.anim.abc_fade_out
                )
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(
            androidx.appcompat.R.anim.abc_fade_in,
            androidx.appcompat.R.anim.abc_fade_out
        )
    }
}