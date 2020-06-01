/*
 * Copyright (c) 2020. Samsruti Dash
 * Author: Samsuti Dash
 *
 */

package com.samsruti.kotlin30days.ui.essentials

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samsruti.kotlin30days.entities.essential.EssentialResourcesData
import com.samsruti.kotlin30days.entities.essential.Features
import com.samsruti.kotlin30days.repository.CovidApiRepository
import com.samsruti.kotlin30days.repository.DataCloudRepository
import kotlinx.coroutines.launch

class EssentialsViewModel : ViewModel() {

    private val repository: CovidApiRepository = CovidApiRepository()
    private val dataCloudRepository: DataCloudRepository = DataCloudRepository()

    private val _text = MutableLiveData<String>().apply {
        value = "This is notifications Fragment"
    }
    val text: LiveData<String> = _text

    private val _mappedEssentials =  MutableLiveData<List<Features>>()
    val mappedEssentials: LiveData<List<Features>>
        get() = _mappedEssentials

    private val _loading =  MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loading

    private val _error =  MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error


    fun getLocalEssentials(lat: String, lon: String) {
        _loading.value = true

        viewModelScope.launch {
            runCatching {
                val resources = dataCloudRepository.getGeoCordinates(lat, lon)
                val essentialData = repository.fetchEssentialData()
                resources?.let {locality->
                    val localityString = locality.principalSubdivision
                    val district: String? = resources.localityInfo?.administrative?.get(1)?.name?.split(" ")?.get(0)

                    _mappedEssentials.value =
                        if(district.isNullOrEmpty()) {
                            mapperHelper(localityString, essentialData)
                        } else {
                            mapperHelper(district, essentialData)
                        }
                }
                _loading.value = false

            }.getOrElse {
                _loading.value = false
                _error.value = it.localizedMessage
            }
        }
    }

    private fun mapperHelper(currentLocation: String, allData: EssentialResourcesData): List<Features>{
        val filteredData = allData.features.filter {
            it.properties.addr.contains(currentLocation)
        }
        return filteredData
    }

}