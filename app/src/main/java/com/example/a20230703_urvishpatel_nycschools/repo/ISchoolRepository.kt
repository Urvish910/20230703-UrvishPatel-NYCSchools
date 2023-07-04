package com.example.a20230703_urvishpatel_nycschools.repo

import com.example.a20230703_urvishpatel_nycschools.model.remote.response.SatResponse
import com.example.a20230703_urvishpatel_nycschools.model.remote.response.SchoolResponse
import retrofit2.Response

interface ISchoolRepository {
    suspend fun getSchoolList() : Response<SchoolResponse>
    suspend fun getSatList(dbn:String) : Response<SatResponse>
}