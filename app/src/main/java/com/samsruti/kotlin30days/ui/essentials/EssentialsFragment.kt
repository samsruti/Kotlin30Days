package com.samsruti.kotlin30days.ui.essentials

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
                        Toast.makeText(activity, essentials[itemPosition].properties.phone,Toast.LENGTH_LONG).show()
                    }
                    R.id.btnMap -> {
                        Toast.makeText(activity, essentials[itemPosition].geometry.toString(),Toast.LENGTH_LONG).show()
                    }
                    R.id.btnWebsite -> {
                        Toast.makeText(activity, essentials[itemPosition].properties.contact,Toast.LENGTH_LONG).show()
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

    fun onClickCall(view: View){

    }

}