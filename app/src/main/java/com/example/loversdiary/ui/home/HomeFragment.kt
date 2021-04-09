package com.example.loversdiary.ui.home

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.loversdiary.R
import com.example.loversdiary.databinding.HomeFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.home_fragment) {

    private val homeViewModel: HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = HomeFragmentBinding.bind(view)

        binding.apply {

            homeFrgSelfNameView.text = homeViewModel.user.user_name
            homeFrgPartnerNameView.text = homeViewModel.user.partner_name

            val daysText = homeViewModel.daysLeft.toString() + " дня"
            homeFrgDaysView.text = daysText
            val monthsText = homeViewModel.monthsLeft.toString() + " месяцев"
            homeFrgMonthsView.text = monthsText
            val yearsText = homeViewModel.yearsLeft.toString() + " лет"
            homeFrgYearsView.text = yearsText
        }

        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.home_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_redirect_to_settings -> {
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