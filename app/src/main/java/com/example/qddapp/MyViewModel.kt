package com.example.qddapp

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.maps.model.LatLng

class MyViewModel : ViewModel() {
    val imageUri = MutableLiveData<Uri>()

    var dataToPass = MutableLiveData<String>()
    var dataToPassCategory = MutableLiveData<String>()
    var dataPositionCreate = MutableLiveData<LatLng>()

    fun updateDataCategory(data: String){
        dataToPassCategory.value = data
    }

    fun updateData(data: String){
        dataToPass.value = data
    }

    fun updatePosition(position: LatLng){
        dataPositionCreate.value = position
    }
}