package com.example.wethero.favouritesViewModel

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wethero.Model.Daily
import com.example.wethero.databinding.DayRowBinding
import com.example.wethero.databinding.FavRowBinding
import com.example.wethero.viewmodel.DailyAdapter

class FavouriteAdapter ( var current: List<Daily>):
    RecyclerView.Adapter<FavouriteAdapter.FavViewHolder>() {
    lateinit var context: Context
    lateinit var binding: FavRowBinding

    class FavViewHolder(var binding: FavRowBinding) : RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavViewHolder {
        context=parent.context
        val inflater: LayoutInflater =parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding= FavRowBinding.inflate(inflater,parent,false)
        return FavouriteAdapter.FavViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return current.size
    }

    override fun onBindViewHolder(holder: FavViewHolder, position: Int) {

    }
}