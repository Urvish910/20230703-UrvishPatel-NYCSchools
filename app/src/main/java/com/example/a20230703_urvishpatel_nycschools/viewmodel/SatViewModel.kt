package com.example.a20230703_urvishpatel_nycschools.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a20230703_urvishpatel_nycschools.model.remote.response.SatResponse
import com.example.a20230703_urvishpatel_nycschools.repo.ISchoolRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SatViewModel @Inject constructor(private val repository: ISchoolRepository):ViewModel() {

    val satSchoolData = MutableLiveData<SatResponse>()
    val message = MutableLiveData<String>()
    val pb = MutableLiveData<Boolean>()

    fun getSatSchool(dbn:String){
        val coroutineExceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
            message.postValue(throwable.message)
            Log.i("checking_error",throwable.message.toString())
            return@CoroutineExceptionHandler
        }
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            pb.postValue(true)
            val response = repository.getSatList(dbn)
            if (!response.isSuccessful) {
                pb.postValue(false)
                message.postValue("Something went wrong, Please try later.")
                return@launch
            }else if(response.body()==null){
                pb.postValue(false)
                message.postValue("No Data Found")
                return@launch
            }
            satSchoolData.postValue(response.body())
            pb.postValue(false)
            return@launch
        }
    }

}