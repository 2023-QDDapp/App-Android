package com.example.qddapp

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.qddapp.Modelos.Categoria
import com.google.android.gms.maps.model.LatLng

class MyViewModel : ViewModel() {
    val imageUri = MutableLiveData<Uri>()

    var dataToPass = MutableLiveData<String>()
    var dataToPassCategory = MutableLiveData<Categoria>()
    var dataPositionCreate = MutableLiveData<LatLng>()

    fun updateDataCategory(data: Categoria){
        dataToPassCategory.value = data
    }

    fun deleteDataCategory(){
        dataToPassCategory.value = Categoria(0, "")
    }

    fun updateData(data: String){
        dataToPass.value = data
    }

    fun updatePosition(position: LatLng){
        dataPositionCreate.value = position
    }
}