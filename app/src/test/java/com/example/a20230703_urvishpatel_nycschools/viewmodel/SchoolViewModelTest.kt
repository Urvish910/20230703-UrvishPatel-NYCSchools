package com.example.a20230703_urvishpatel_nycschools.viewmodel

import androidx.arch.core.executor.*
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.a20230703_urvishpatel_nycschools.model.remote.api.ApiService
import com.example.a20230703_urvishpatel_nycschools.model.remote.response.SchoolResponse
import io.mockk.mockk
import io.mockk.spyk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner


@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class SchoolViewModelTest {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()
    lateinit var viewModel: SchoolViewModel
    lateinit var retrofit: ApiService

    @Before
    fun setUp() {
        viewModel = spyk()
        retrofit = mockk()
    }

    @After
    fun tearDown() {

    }

    @Test
    fun TestResponsefromAPi() {

        val dummy = SchoolResponse()

        assert(viewModel.schoolMLD.value?.isEmpty() == false)
        assert(viewModel.schoolMLD.value?.size == 0)
        assert(viewModel.schoolMLD.value?.isEmpty() == true)
    }

}