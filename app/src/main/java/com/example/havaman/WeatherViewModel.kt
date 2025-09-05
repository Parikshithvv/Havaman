package com.example.havaman

import android.net.Network
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.havaman.api.RetrofitInstance
import com.example.havaman.api.WeatherModel
import com.example.havaman.api.constant
import com.example.havaman.api.networkrespose
import kotlinx.coroutines.launch

class WeatherViewModel :ViewModel() {

    private val weatherApi = RetrofitInstance.weatherApi
    private val _weatherresult = MutableLiveData<networkrespose<WeatherModel>>()
    val weatherResult : LiveData<networkrespose<WeatherModel>> = _weatherresult


    fun getData(city : String){
        _weatherresult.value = networkrespose.Loading
        viewModelScope.launch {
            try {


                val response = weatherApi.getweather(constant.apikey, city)
                if (response.isSuccessful) {
                    response.body()?.let {
                        _weatherresult.value = networkrespose.Success(it)
                    }

                } else {
                    _weatherresult.value = networkrespose.Error("Failed to Load Data")
                }
            }
            catch (e : Exception){
                _weatherresult.value = networkrespose.Error("Failed to load data")
            }



        }





    }
}