package com.example.a20230703_urvishpatel_nycschools.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.a20230703_urvishpatel_nycschools.R
import com.example.a20230703_urvishpatel_nycschools.adapter.SchoolAdapter
import com.example.a20230703_urvishpatel_nycschools.databinding.ActivityMainBinding
import com.example.a20230703_urvishpatel_nycschools.viewmodel.SchoolViewModel
import dagger.hilt.android.AndroidEntryPoint

/*
* The activity is implemented using the dataBinding and viewBinding
*/
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var bindingUtil: ActivityMainBinding
    lateinit var schoolViewModel: SchoolViewModel
    lateinit var adapter: SchoolAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingUtil = DataBindingUtil.setContentView(this, R.layout.activity_main)


        initViewmodel()
        initFunctionCall()
        observerLiveData()


    }

    private fun observerLiveData() {
        schoolViewModel.progressBarMLD.observe(this){
            if (it)
                bindingUtil.pb.visibility = View.VISIBLE
            else
                bindingUtil.pb.visibility = View.GONE
        }
        bindingUtil.data = schoolViewModel
        schoolViewModel.schoolMLD.observe(this) {
            adapter = SchoolAdapter(it)
            bindingUtil.rvSchool.adapter = adapter
            adapter.setOnClickbnt { schoolResponseItem, i ->
                val intent = Intent(baseContext,SATschoolActivity::class.java)
                intent.putExtra("dbn",schoolResponseItem.dbn)
                startActivity(intent)
            }

        }

    }

    private fun initFunctionCall() {
        if (schoolViewModel.schoolMLD.value.isNullOrEmpty()){
            schoolViewModel.getSchoolList()
        }

    }

    private fun initViewmodel() {
        bindingUtil.lifecycleOwner = this
        schoolViewModel = ViewModelProvider(this)[SchoolViewModel::class.java]
    }
}