package com.buzzware.zabellauser.ui.dashboard

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.buzzware.zabellauser.R
import com.buzzware.zabellauser.databinding.FragmentWalletBinding
import com.buzzware.zabellauser.ui.adapters.CreditCardAdapter
import com.buzzware.zabellauser.ui.model.PaymentMethodsResponse
import com.buzzware.zabellauser.ui.utils.UserSession
import com.stripe.android.Stripe
import com.stripe.android.model.CardParams
import com.stripe.android.model.ConfirmSetupIntentParams
import com.stripe.android.model.PaymentMethodCreateParams
import com.stripe.android.model.StripeIntent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class WalletFragment : Fragment() {

    //private var db = Firebase.firestore
    private lateinit var stripe: Stripe
    private lateinit var creditCardAdapter: CreditCardAdapter
    private var secrat = ""
    private var cardList: ArrayList<PaymentMethodsResponse.PaymentMethod> = ArrayList()

    private val binding: FragmentWalletBinding by lazy {
        FragmentWalletBinding.inflate(layoutInflater)
    }
    private lateinit var fragmentContext: Context

    //private var cardList: ArrayList<PaymentMethodsResponse.PaymentMethod> = ArrayList()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        stripe = Stripe(
            requireContext(),
            "pk_test_51PgBExCo08Oa4W8HRRlISwH7IOZRW42joDX0KpJRo7RK4tZhrz29Cout7tSsBEWCeODsr7IhT8jQGNiUrMIwwR0h00jZcUoUkr"
        )

        updateAdapter()
        binding.addCardBtn.setOnClickListener {

        }
        return binding.root
    }

    private fun updateAdapter() {
        binding.cardListRv.layoutManager = LinearLayoutManager(fragmentContext)
        creditCardAdapter = CreditCardAdapter(fragmentContext, arrayListOf()) { pmID, pos ->
           // showAlertDialog(pmID, pos)

        }
        binding.cardListRv.adapter = creditCardAdapter
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        fragmentContext = context
    }

}