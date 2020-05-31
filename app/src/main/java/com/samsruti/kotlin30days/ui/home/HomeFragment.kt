package com.samsruti.kotlin30days.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.samsruti.kotlin30days.R
import com.samsruti.kotlin30days.entities.covid19data.Covid19AllData
import kotlinx.android.synthetic.main.card_layout_stats.*
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        refreshPage()

        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            text_home.text = it
        })

        homeViewModel.loading.observe(viewLifecycleOwner, Observer {loadingStatus ->
            if (loadingStatus) showLoadingView()
            else hideLoadingView()
        })

        homeViewModel.statewiseLiveData.observe(viewLifecycleOwner, Observer {covidAllData ->
            updateHomePageView(covidAllData)
        })
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnCallHelpline.setOnClickListener {
            makeHelpLineCall()
        }

        btnRefreshStats.setOnClickListener {
            refreshPage()
        }
    }

    private fun makeHelpLineCall() {

    }

    private fun updateHomePageView(covidAllData: Covid19AllData?) {
        covidAllData?.let {
            val totalCountData = covidAllData.statewise[0]
            tvTotalCountActive.text = totalCountData.active.toString()
            tvTotalCountDeath.text = totalCountData.deaths.toString()
            tvTotalCountConfirmed.text = totalCountData.confirmed.toString()
            tvTotalCountRecovered.text = totalCountData.recovered.toString()

            tvDailyIncreaseDeath.text = totalCountData.deltaDeaths.toString()
            tvDailyIncreaseConfirmed.text = totalCountData.deltaConfirmed.toString()
            tvDailyIncreaseRecovered.text = totalCountData.deltaRecovered.toString()
        }
    }

    private fun refreshPage(){
        homeViewModel.updateStatewiseData()
    }

    private fun showLoadingView() {
    }

    private fun hideLoadingView() {
    }
}