package com.samsruti.kotlin30days.ui.home

import android.os.Bundle
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.samsruti.kotlin30days.R
import com.samsruti.kotlin30days.entities.covid19data.Covid19AllData
import com.samsruti.kotlin30days.extensions.formatToLastUpdatedOn
import kotlinx.android.synthetic.main.card_layout_stats.*
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by lazy {
        ViewModelProvider(this).get(HomeViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnCallHelpline.setOnClickListener {
            makeHelpLineCall()
        }

        btnRefreshStats.setOnClickListener {
            refreshPage()
        }

        with(homeViewModel){
            loading.observe(viewLifecycleOwner, Observer {loadingStatus ->
                if (loadingStatus) showLoadingView()
                else hideLoadingView()
            })

            statewiseLiveData.observe(viewLifecycleOwner, Observer {covidAllData ->
                updateHomePageView(covidAllData)
            })
        }

        refreshPage()
    }

    private fun makeHelpLineCall() {

    }

    private fun updateHomePageView(covidAllData: Covid19AllData?) {
        covidAllData?.let {
            val totalCountData = covidAllData.casesTimeSeries.lastOrNull()
            val activeData = covidAllData.statewise.firstOrNull()



            activeData?.let {
                tvTotalCountActive.text = it.active.toString()
                tvLastUpdatedOn.text = it.lastUpdatedTime.formatToLastUpdatedOn()
            }

            totalCountData?.let {

                tvTotalCountDeath.text = it.totalDeceased.toString()
                tvTotalCountConfirmed.text = it.totalConfirmed.toString()
                tvTotalCountRecovered.text = it.totalRecovered.toString()

                tvDailyIncreaseDeath.text = it.dailyDeceased.toString()
                tvDailyIncreaseConfirmed.text = it.dailyConfirmed.toString()
                tvDailyIncreaseRecovered.text = it.dailyDeceased.toString()
            }

        }
    }

    private fun refreshPage(){
        homeViewModel.updateStatewiseData()
    }

    private fun showLoadingView() {
        loadingGroup.visibility = View.VISIBLE
        statsCardView.visibility = View.GONE
    }

    private fun hideLoadingView() {
        loadingGroup.visibility = View.GONE
        statsCardView.visibility = View.VISIBLE
    }

    private fun showErrorView(message: String){
        loadingGroup.visibility = View.GONE
        statsCardView.visibility = View.GONE
        tvErrorText.text = message
    }


}