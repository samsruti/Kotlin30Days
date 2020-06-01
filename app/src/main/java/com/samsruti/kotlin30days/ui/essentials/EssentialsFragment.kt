package com.samsruti.kotlin30days.ui.essentials

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.samsruti.kotlin30days.R
import kotlinx.android.synthetic.main.fragment_essentials.*
import kotlinx.android.synthetic.main.item_essential.*
import kotlinx.android.synthetic.main.item_essential.view.*
import kotlinx.android.synthetic.main.item_essential.view.btnCall

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
        val textView: TextView = root.findViewById(R.id.text_notifications)
        essentialsViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        essentialsViewModel.text.observe(viewLifecycleOwner, Observer {
            text_notifications.text = it
        })

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
                            lat = essentials[itemPosition].geometry.coordinates[0].toString(),
                            lon = essentials[itemPosition].geometry.coordinates[1].toString()
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

    private fun hideLoadingView() {
    }

    private fun showLoadingView() {
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val (lat, lon) = Pair("18.503470399999998","73.96756400000001")

        btnUseLocation.setOnClickListener {
            essentialsViewModel.getLocalEssentials(lat, lon)
        }
    }

    private fun onClickCall(contactNo: String?){
        val callIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$contactNo"))
        startActivity(callIntent)
    }

    private fun onClickMap(lat: String?, lon: String?){
        val geoString = "geo:$lat,$lon?z=14"
        val callIntent = Intent(Intent.ACTION_VIEW, Uri.parse(geoString))
        startActivity(callIntent)
    }

    private fun onClickWebsite(website: String?){
        val callIntent = Intent(Intent.ACTION_VIEW, Uri.parse(website))
        startActivity(callIntent)
    }

}