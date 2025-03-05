package com.buzzware.zabellauser.ui.dashboard

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.buzzware.zabellauser.R
import com.buzzware.zabellauser.databinding.FragmentHomeBinding
import com.buzzware.zabellauser.ui.activities.YourRideActivity
import com.stripe.android.PaymentConfiguration
import com.stripe.android.Stripe

class HomeFragment : Fragment() {

    private val binding: FragmentHomeBinding by lazy {
        FragmentHomeBinding.inflate(layoutInflater)
    }
    private lateinit var fragmentContext: Context

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding.apply {

            bookRideBtn.setOnClickListener {
                startActivity(Intent(fragmentContext,YourRideActivity::class.java))
                requireActivity().overridePendingTransition(
                    androidx.appcompat.R.anim.abc_fade_in,
                    androidx.appcompat.R.anim.abc_fade_out
                )
            }
        }
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        fragmentContext = context
    }
}