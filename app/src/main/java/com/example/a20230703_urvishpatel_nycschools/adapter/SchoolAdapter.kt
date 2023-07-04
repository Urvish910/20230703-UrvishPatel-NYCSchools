package com.example.a20230703_urvishpatel_nycschools.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.a20230703_urvishpatel_nycschools.R
import com.example.a20230703_urvishpatel_nycschools.databinding.ViewHolderSchoolBinding
import com.example.a20230703_urvishpatel_nycschools.model.remote.response.SchoolResponseItem

class SchoolAdapter(private val list:List<SchoolResponseItem>):RecyclerView.Adapter<SchoolAdapter.SchoolViewHolder>() {
    inner class SchoolViewHolder(val binding: ViewHolderSchoolBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(list:SchoolResponseItem){
            binding.data = list
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SchoolViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ViewHolderSchoolBinding>(layoutInflater, R.layout.view_holder_school,parent,false)
        return SchoolViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SchoolViewHolder, position: Int) {
        holder.bind(list[position])
        holder.binding.btn.setOnClickListener {
            if (this::btnlistner.isInitialized){
                btnlistner(list[position],position)
            }
        }
    }

    override fun getItemCount(): Int {
        return  list.size
    }
    lateinit var btnlistner:(SchoolResponseItem,Int)->Unit

    fun setOnClickbnt(listner:(SchoolResponseItem,Int)->Unit){
        btnlistner =listner
    }
}