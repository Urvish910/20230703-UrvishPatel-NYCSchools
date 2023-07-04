package com.example.a20230703_urvishpatel_nycschools.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.a20230703_urvishpatel_nycschools.databinding.ActivitySatschoolBinding
import com.example.a20230703_urvishpatel_nycschools.viewmodel.SatViewModel
import dagger.hilt.android.AndroidEntryPoint

/*
* this is used to to get the SAT result and displaying its details
* */
@AndroidEntryPoint
class SATschoolActivity : AppCompatActivity() {
    lateinit var binding: ActivitySatschoolBinding
    lateinit var viewModel: SatViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySatschoolBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViewModel()
        intiFunctionCAll()
        intiObserver()
    }

    @SuppressLint("SetTextI18n")
    private fun intiObserver() {
        viewModel.satSchoolData.observe(this){
            if(!it.isEmpty()){
                binding.data = it[0]
            }else{
                binding.tvSchoolName.text = "Sorry No Data is Available. Please try later"
            }
        }
        viewModel.pb.observe(this){

            if (it)
                binding.pbCheck.visibility = View.VISIBLE
            else
                binding.pbCheck.visibility = View.GONE
        }

    }

    private fun intiFunctionCAll() {
        val dbn = intent.getStringExtra("dbn")
        if (dbn != null) {
            viewModel.getSatSchool(dbn)
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this)[SatViewModel::class.java]
    }
}