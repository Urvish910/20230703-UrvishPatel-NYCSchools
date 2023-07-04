package com.example.a20230703_urvishpatel_nycschools.repo

import com.example.a20230703_urvishpatel_nycschools.model.remote.api.ApiService
import com.example.a20230703_urvishpatel_nycschools.model.remote.response.SatResponse
import com.example.a20230703_urvishpatel_nycschools.model.remote.response.SchoolResponse
import retrofit2.Response
import javax.inject.Inject

class SchoolRepository @Inject constructor(private val apiService: ApiService): ISchoolRepository {
    override suspend fun getSchoolList(): Response<SchoolResponse> {
        return apiService.getSchoolList()
    }

    override suspend fun getSatList(dbn: String): Response<SatResponse> {
        return apiService.getSatSchool(dbn)
    }

}