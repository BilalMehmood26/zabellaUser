package com.buzzware.zabellauser.ui.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.buzzware.zabellauser.R
import com.buzzware.zabellauser.databinding.ActivityEditProfileBinding
import com.buzzware.zabellauser.databinding.ActivitySelectVehicleBinding

class SelectVehicleActivity : AppCompatActivity() {

    private val binding: ActivitySelectVehicleBinding by lazy {
        ActivitySelectVehicleBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}