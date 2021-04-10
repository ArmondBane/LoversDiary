package com.example.loversdiary.ui.home

import android.content.Context
import android.os.Binder
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.loversdiary.R
import com.example.loversdiary.databinding.HomeFragmentBinding
import com.example.loversdiary.util.exhaustive
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import java.time.Instant
import java.time.LocalDate
import java.time.Period
import java.time.ZoneId

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.home_fragment) {

    private val homeViewModel: HomeViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = HomeFragmentBinding.bind(view)

        binding.apply {

            homeViewModel.user.observe(viewLifecycleOwner) {
                homeFrgSelfNameView.text = it.user_name
                homeFrgPartnerNameView.text = it.partner_name

                val period = Period.between(
                        Instant.ofEpochMilli(it.date_of_start).atZone(ZoneId.systemDefault()).toLocalDate(),
                        LocalDate.now()
                )

                val daysText = period.days.toString() +
                when (period.days) {
                    1 -> " день"
                    in 5..20 -> " дней"
                    21 -> " день"
                    31 -> " день"
                    else -> (" дня")
                }
                homeFrgDaysView.text = daysText
                val monthsText = period.months.toString() +
                when (period.months) {
                    1 -> " месяц"
                    in 2..4 -> " месяца"
                    else -> (" месяцев")
                }
                homeFrgMonthsView.text = monthsText
                val yearsText = period.years.toString() +
                when (period.years % 100) {
                    0 -> " лет"
                    in 5..20 -> " лет"
                    1 -> " год"
                    21 -> " год"
                    31 -> " год"
                    41 -> " год"
                    51 -> " год"
                    61 -> " год"
                    71 -> " год"
                    81 -> " год"
                    91 -> " год"
                    else -> (" года")
                }
                homeFrgYearsView.text = yearsText
            }
        }

        setFragmentResultListener("settings_request") { _, bundle ->
            val result = bundle.getInt("settings_request")
            homeViewModel.onSettingsResult(result)
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            homeViewModel.homeEvent.collect { event ->
                when (event) {
                    is HomeViewModel.HomeEvent.ShowSettingsSavedConfirmationMessage -> {
                        Snackbar.make(requireView(), event.msg, Snackbar.LENGTH_SHORT).show()
                    }
                }.exhaustive
            }
        }

        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.home_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_redirect_to_settings -> {
                val action = HomeFragmentDirections.actionHomeFragmentToSettingsFragment()
                findNavController().navigate(action)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as AppCompatActivity?)!!.supportActionBar?.show()
    }
}