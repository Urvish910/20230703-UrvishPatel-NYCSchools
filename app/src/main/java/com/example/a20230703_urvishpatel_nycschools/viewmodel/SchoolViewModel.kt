package com.example.a20230703_urvishpatel_nycschools.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a20230703_urvishpatel_nycschools.model.remote.api.ApiClient
import com.example.a20230703_urvishpatel_nycschools.model.remote.api.ApiService
import com.example.a20230703_urvishpatel_nycschools.model.remote.response.SchoolResponse
import com.example.a20230703_urvishpatel_nycschools.repo.ISchoolRepository
import dagger.Provides
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import javax.inject.Inject

@HiltViewModel
class SchoolViewModel @Inject constructor(private val repository: ISchoolRepository):ViewModel() {
    val schoolMLD = MutableLiveData<SchoolResponse>()
    val messageMLD = MutableLiveData<String>()
    val progressBarMLD = MutableLiveData<Boolean>()

    fun getSchoolList(){
        progressBarMLD.postValue(true)
        val coroutineExceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
            messageMLD.postValue(throwable.message)
            return@CoroutineExceptionHandler
        }
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            val response = repository.getSchoolList()
            if(!response.isSuccessful){
                messageMLD.postValue("Something Went Wrong, Please try later.")
                progressBarMLD.postValue(false)
                return@launch
            }else if(response.body().isNullOrEmpty()){
                messageMLD.postValue("Data Not Found")
                progressBarMLD.postValue(false)
                return@launch
            }
            schoolMLD.postValue(response.body())
            progressBarMLD.postValue(false)
            return@launch
        }
    }
}