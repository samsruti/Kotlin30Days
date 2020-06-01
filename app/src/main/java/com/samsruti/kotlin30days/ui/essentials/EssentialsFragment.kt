/*
 * Copyright (c) 2020. Samsruti Dash
 * Author: Samsuti Dash
 *
 */

package com.samsruti.kotlin30days.ui.essentials

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.samsruti.kotlin30days.R
import kotlinx.android.synthetic.main.fragment_essentials.*

class EssentialsFragment : Fragment() {

    private lateinit var essentialsViewModel: EssentialsViewModel

    private var essentialsListAdapter: EssentialsListAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        essentialsViewModel =
            ViewModelProvider(this).get(EssentialsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_essentials, container, false)

        essentialsViewModel.loading.observe(viewLifecycleOwner, Observer {loadingStatus ->
            if (loadingStatus) showLoadingView()
            else hideLoadingView()
        })

        essentialsViewModel.mappedEssentials.observe(viewLifecycleOwner, Observer {essentials ->


            essentialsListAdapter = EssentialsListAdapter(essentials) { itemView, itemPosition->
                when(itemView.id){
                    R.id.btnCall -> {
                        onClickCall(essentials[itemPosition].properties.phone)
                    }
                    R.id.btnMap -> {
                        onClickMap(
                            lat = essentials[itemPosition].geometry.coordinates[1].toString(),
                            lon = essentials[itemPosition].geometry.coordinates[0].toString()
                        )
                    }
                    R.id.btnWebsite -> {
                        onClickWebsite(essentials[itemPosition].properties.contact)
                    }
                }


            }
            rvEssentials.adapter = essentialsListAdapter
        })
        
        return root
    }

    private fun showLoadingView() {
        loadingGroup.visibility = View.VISIBLE
        rvEssentials.visibility = View.GONE
    }

    private fun hideLoadingView() {
        loadingGroup.visibility = View.GONE
        rvEssentials.visibility = View.VISIBLE
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        TODO: Use the realtime location

        val (lat, lon) = Pair("18.503470399999998","73.96756400000001")

        btnUseLocation.setOnClickListener {
            essentialsViewModel.getLocalEssentials(lat, lon)
        }
    }

    private fun onClickCall(contactNo: String?){
        val callIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$contactNo"))
        if (activity?.packageManager?.let { callIntent.resolveActivity(it) } != null) {
            startActivity(callIntent)
        }
    }

    private fun onClickMap(lat: String?, lon: String?){
        val geoString = "geo:$lat,$lon?z=14"
        val gmmIntentUri = Uri.parse(geoString)
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.setPackage("com.google.android.apps.maps")
        if (activity?.packageManager?.let { mapIntent.resolveActivity(it) } != null) {
            startActivity(mapIntent)
        }
        startActivity(mapIntent)
    }

    private fun onClickWebsite(website: String?){
        val websiteIntent = Intent(Intent.ACTION_VIEW, Uri.parse(website))
        if (activity?.packageManager?.let { websiteIntent.resolveActivity(it) } != null) {
            startActivity(websiteIntent)
        }
    }

}