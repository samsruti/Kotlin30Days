/*
 * Copyright (c) 2020. Samsruti Dash
 * Author: Samsuti Dash
 *
 */

package com.samsruti.kotlin30days.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samsruti.kotlin30days.entities.covid19data.Covid19AllData
import com.samsruti.kotlin30days.repository.CovidApiRepository
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val repository: CovidApiRepository = CovidApiRepository()

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    private val _statewiseDataLiveData =  MutableLiveData<Covid19AllData>()
    val statewiseLiveData: LiveData<Covid19AllData>
        get() = _statewiseDataLiveData

    private val _loading =  MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loading

    private val _error =  MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error


    fun updateStatewiseData(){
        _loading.value = true
        viewModelScope.launch {
            try {
                _statewiseDataLiveData.value = repository.fetchHomeDetails()
            } catch (e : Exception){
                _statewiseDataLiveData.value = null
                _error.value = e.localizedMessage
            }
            _loading.value = false
        }
    }
}