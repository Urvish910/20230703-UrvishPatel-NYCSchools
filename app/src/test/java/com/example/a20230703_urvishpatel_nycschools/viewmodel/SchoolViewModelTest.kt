package com.example.a20230703_urvishpatel_nycschools.viewmodel

import androidx.arch.core.executor.*
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.a20230703_urvishpatel_nycschools.model.remote.response.SchoolResponse
import com.example.a20230703_urvishpatel_nycschools.repo.ISchoolRepository
import com.google.gson.Gson
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify
import retrofit2.Response


@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class SchoolViewModelTest {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()
    lateinit var viewModel: SchoolViewModel

    @Mock
    lateinit var repository: ISchoolRepository

    @Mock
    lateinit var schoolsObserver: Observer<SchoolResponse>


    @Before
    fun setUp() {
        viewModel = SchoolViewModel(repository)

    }

    @Test
    fun TestResponsefromAPi() {

        runBlocking {
            val json = TestConstants.getallSchoolResponse
            val fakeResponse :Response<SchoolResponse> = Response.success(Gson().fromJson(json,SchoolResponse::class.java))

            Mockito.`when`(repository.getSchoolList()).thenReturn(fakeResponse)

            viewModel.schoolMLD.observeForever(schoolsObserver)
            viewModel.getSchoolList()

            verify(schoolsObserver).onChanged(fakeResponse.body()!!)

            viewModel.schoolMLD.removeObserver(schoolsObserver)

        }

    }

}