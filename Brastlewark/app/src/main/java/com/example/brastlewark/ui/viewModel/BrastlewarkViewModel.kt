package com.example.brastlewark.ui.viewModel

import android.content.Context
import android.net.ConnectivityManager
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.brastlewark.data.CitizenModel
import com.example.brastlewark.domain.GetAllCitizen
import com.example.brastlewark.domain.GetAllCitizenRoom
import com.example.brastlewark.domain.InsertAllCitizenRoom
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class BrastlewarkViewModel @Inject constructor(private val allcitizen:GetAllCitizen, @ApplicationContext private val appContext: Context, private val allCitizenRoom: GetAllCitizenRoom, private val insertAllCitizenRoom: InsertAllCitizenRoom):ViewModel() {


    val citizen= MutableLiveData<ArrayList<CitizenModel>>()
    val isLoading = MutableLiveData<Boolean>()

    fun onCreate(){

        val conexion = (appContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).activeNetwork


        if(conexion != null) {
            viewModelScope.launch {
                isLoading.postValue(true)

                val result: ArrayList<CitizenModel>? = allcitizen()?.population

                if (!result.isNullOrEmpty()) {
                    citizen.postValue(result!!)
                    isLoading.postValue(false)
                } else {
                    Toast.makeText(appContext, "Error la lista esta vacia", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }else{
            viewModelScope.launch {
                citizen.postValue(allCitizenRoom.invoke() as ArrayList<CitizenModel>)
            }
        }
    }

    fun insertCitizen(citizen: CitizenModel){
        viewModelScope.launch {
            insertAllCitizenRoom.insertAllCitizenRoom(citizen)
        }
    }
}