package com.buzzware.zabellauser.ui.activities

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.buzzware.zabellauser.R
import com.buzzware.zabellauser.databinding.ActivityDashboardBinding
import com.buzzware.zabellauser.ui.dashboard.ChatFragment
import com.buzzware.zabellauser.ui.dashboard.HomeFragment
import com.buzzware.zabellauser.ui.dashboard.ProfileFragment
import com.buzzware.zabellauser.ui.dashboard.SchduleRideFragment
import com.buzzware.zabellauser.ui.dashboard.WalletFragment

class DashboardActivity : AppCompatActivity() {

    private val binding : ActivityDashboardBinding by lazy {
        ActivityDashboardBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        clearAllTab()
        setHomeTab()
        setListener()

    }


    private fun setListener() {

        binding.logoutIV.setOnClickListener {
            //logout()
        }

        binding.homeTab.setOnClickListener {
            clearAllTab()
            setHomeTab()
        }

        binding.rideTab.setOnClickListener {
            clearAllTab()
            setRideTab()
        }

        binding.chatTab.setOnClickListener {
            clearAllTab()
            setChatTab()
        }

        binding.walletTab.setOnClickListener {
            clearAllTab()
            setWalletTab()
        }

        binding.profileTab.setOnClickListener {
            clearAllTab()
            setProfileTab()
        }
    }

    private fun setHomeTab() {
        binding.homeTab.setImageResource(R.drawable.bottom_home_selected)
        binding.titleTV.text = "Home"
        loadFragment(HomeFragment())
    }

    private fun setRideTab() {
        binding.rideTab.setImageResource(R.drawable.bottom_ride_selected)
        binding.titleTV.text = "Schedule a Ride"
        loadFragment(SchduleRideFragment())
    }

    private fun setChatTab() {
        binding.chatTab.setImageResource(R.drawable.bottom_chat_selected)
        binding.titleTV.text = "Messages"
        loadFragment(ChatFragment())
    }

    private fun setWalletTab() {
        binding.walletTab.setImageResource(R.drawable.bottom_wallet_selected)
        binding.titleTV.text = "Wallet"
        loadFragment(WalletFragment())
    }

    private fun setProfileTab() {
        binding.profileTab.setImageResource(R.drawable.bottom_profile_selected)
        binding.logoutIV.visibility = View.VISIBLE
        binding.titleTV.text = "Profile"
        loadFragment(ProfileFragment())
    }

    private fun clearAllTab() {
        binding.logoutIV.visibility = View.GONE
        binding.homeTab.setImageResource(R.drawable.bottom_home_unselected)
        binding.rideTab.setImageResource(R.drawable.bottom_ride_unselected)
        binding.chatTab.setImageResource(R.drawable.bottom_chat_unselected)
        binding.walletTab.setImageResource(R.drawable.bottom_wallet_unselected)
        binding.profileTab.setImageResource(R.drawable.bottom_profile_unselected)
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.setCustomAnimations(androidx.appcompat.R.anim.abc_fade_in, com.google.android.material.R.anim.abc_fade_out)
        transaction.replace(binding.container.id, fragment)
        transaction.commit()
    }
}