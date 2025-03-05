package com.buzzware.zabellauser.ui.dashboard

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.buzzware.zabellauser.R
import com.buzzware.zabellauser.databinding.FragmentProfileBinding
import com.buzzware.zabellauser.ui.activities.BookingActivity
import com.buzzware.zabellauser.ui.activities.EditProfileActivity
import com.buzzware.zabellauser.ui.activities.NotificationActivity
import com.buzzware.zabellauser.ui.utils.UserSession


class ProfileFragment : Fragment() {

    private val binding: FragmentProfileBinding by lazy {
        FragmentProfileBinding.inflate(layoutInflater)
    }

    private lateinit var fragmentContext: Context

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setListener()

        return binding.root
    }

    private fun setListener() {

        binding.apply {

            /*nameTV.text = UserSession.user.userName
            if (UserSession.user.image.equals("")) {
                profileIV.setImageResource(R.drawable.chat_dummy)
            } else {
                Glide.with(requireActivity()).load(UserSession.user.image).into(profileIV)
            }*/
            accountLayout.setOnClickListener {
                startActivity(Intent(fragmentContext, EditProfileActivity::class.java))
                (fragmentContext as Activity).overridePendingTransition(
                    androidx.appcompat.R.anim.abc_fade_in,
                    androidx.appcompat.R.anim.abc_fade_out
                )
            }

            notificationLayout.setOnClickListener {
                startActivity(Intent(fragmentContext, NotificationActivity::class.java))
                (fragmentContext as Activity).overridePendingTransition(
                    androidx.appcompat.R.anim.abc_fade_in,
                    androidx.appcompat.R.anim.abc_fade_out
                )
            }

            driverLayout.setOnClickListener {
                startActivity(Intent(fragmentContext, BookingActivity::class.java))
                (fragmentContext as Activity).overridePendingTransition(
                    androidx.appcompat.R.anim.abc_fade_in,
                    androidx.appcompat.R.anim.abc_fade_out
                )
            }
        }

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        fragmentContext = context
    }
}