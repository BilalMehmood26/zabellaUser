package com.buzzware.zabellauser.ui.dashboard

import android.app.TimePickerDialog
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.GridLayoutManager
import com.buzzware.zabellauser.R
import com.buzzware.zabellauser.databinding.FragmentSchduleRideBinding
import com.buzzware.zabellauser.ui.adapters.CalendarAdapter
import com.stripe.android.Stripe
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.util.Calendar

class SchduleRideFragment : Fragment() {

    private val binding: FragmentSchduleRideBinding by lazy {
        FragmentSchduleRideBinding.inflate(layoutInflater)
    }

    private lateinit var fragmentContext: Context
    private lateinit var selectedDate: LocalDate

    private var destAddress: String = ""
    private var destLat: Double = 0.0
    private var destLng: Double = 0.0
    private var distance: Double = 0.0
    private var schduleDateInMillis: Long = 0
    private var pickUpAddress: String = "0"
    private var pickUpLat: Double = 0.0
    private var pickUpLng: Double = 0.0
    private var carType: String = ""
    private var cardType: String = ""
    private var time: Long = 0

    private var secrat = ""
    private var paymentMethod = ""
    lateinit var stripe: Stripe

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding.apply {

            stripe = Stripe(
                fragmentContext,
                "pk_test_51PgBExCo08Oa4W8HRRlISwH7IOZRW42joDX0KpJRo7RK4tZhrz29Cout7tSsBEWCeODsr7IhT8jQGNiUrMIwwR0h00jZcUoUkr"
            )
            pickUpLoc.setOnClickListener {
                /*addressesDialogFragment =
                    AddressesDialogFragment { address, city, postalCode, latlng ->
                        pickUpAddress = address
                        pickUpLat = latlng.latitude
                        pickUpLng = latlng.longitude
                        yourLocation.setText("$address, $city")
                    }
                addressesDialogFragment.show(childFragmentManager, "Pickup Location")*/
            }

            dropOffLoc.setOnClickListener {
                /*addressesDialogFragment =
                    AddressesDialogFragment { address, city, postalCode, latlng ->
                        destAddress = address
                        destLat = latlng.latitude
                        destLng = latlng.longitude
                        dropOffTv.setText("$address, $city")
                    }
                addressesDialogFragment.show(childFragmentManager, "Dropoff Location")*/
            }


            /*  createFirstStep(UserSession.user.stripeCustid!!)
            getCardList(UserSession.user.stripeCustid!!)*/

            /*
                        pendingCalender.setOnDateChangeListener { view, year, month, dayOfMonth ->
                            val calendar = Calendar.getInstance()
                            calendar.set(year, month, dayOfMonth)
                            schduleDateInMillis = calendar.timeInMillis

                        }*/

            /*bookRideBtn.setOnClickListener {
                val destLatLng = isWithinFiveMiles(pickUpLat, pickUpLng, destLat, destLng)
                when {
                    pickUpAddress.isEmpty() -> Toast.makeText(
                        fragmentContext,
                        "Pickup Address is Empty",
                        Toast.LENGTH_SHORT
                    ).show()

                    destAddress.isEmpty() -> Toast.makeText(
                        fragmentContext,
                        "Drop Off Address is Empty",
                        Toast.LENGTH_SHORT
                    ).show()

                    destLatLng.not() -> Toast.makeText(
                        fragmentContext,
                        "Sorry FC does not travel out of a 5 mile radius",
                        Toast.LENGTH_SHORT
                    ).show()

                    else -> createRide()
                }
            }*/

            binding.prevIV.setOnClickListener {
                selectedDate = selectedDate.minusMonths(1)
                setMonthView()
            }

            binding.nextIV.setOnClickListener {
                selectedDate = selectedDate.plusMonths(1)
                setMonthView()
            }

            binding.timePicker.setOnClickListener {
                showTimePicker()
            }

        }

        setCalendar()

        return binding.root
    }

    private fun showTimePicker() {
        val cal = Calendar.getInstance()
        val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
            cal.set(android.icu.util.Calendar.HOUR_OF_DAY, hour)
            cal.set(android.icu.util.Calendar.MINUTE, minute)
            time = cal.timeInMillis
            binding.timeTv.setText(SimpleDateFormat("HH:mm").format(cal.time))
        }

        TimePickerDialog(
            fragmentContext,
            timeSetListener,
            cal.get(android.icu.util.Calendar.HOUR_OF_DAY),
            cal.get(android.icu.util.Calendar.MINUTE),
            true
        ).show()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setCalendar() {
        selectedDate = LocalDate.now()
        setMonthView()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setMonthView() {
        binding.apply {
            monthYearTV.text = monthYearFromDate(selectedDate)
            val daysInMonth = daysInMonthArray(selectedDate)

            val calendarAdapter = CalendarAdapter(daysInMonth, selectedDate) { dateinText, dateStamp ->
                    schduleDateInMillis = dateStamp
                    Log.d("Logger", "setMonthView: $dateinText")
                }
            val layoutManager = GridLayoutManager(requireActivity(), 7)
            calendarRecyclerView.layoutManager = layoutManager
            calendarRecyclerView.adapter = calendarAdapter
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun monthYearFromDate(date: LocalDate): String {
        val formatter = DateTimeFormatter.ofPattern("MMMM yyyy")
        return date.format(formatter)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun daysInMonthArray(date: LocalDate): ArrayList<String> {
        val daysInMonthArray = ArrayList<String>()
        val yearMonth = YearMonth.from(date)
        val daysInMonth = yearMonth.lengthOfMonth()
        val firstOfMonth = selectedDate.withDayOfMonth(1)
        val dayOfWeek = firstOfMonth.dayOfWeek.value

        for (i in 1..42) {
            if (i <= dayOfWeek || i > daysInMonth + dayOfWeek) {
                daysInMonthArray.add("")
            } else {
                daysInMonthArray.add((i - dayOfWeek).toString())
            }
        }

        return daysInMonthArray
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        fragmentContext = context
    }
}