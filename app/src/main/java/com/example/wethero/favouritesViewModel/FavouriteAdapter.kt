package com.example.wethero.favouritesViewModel

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.wethero.databinding.FavRowBinding
import com.example.wethero.favouriteModel.FavClickInterface
import com.example.wethero.favouriteModel.FavRecyclerModel

class FavouriteAdapter (var listen: FavClickInterface):
    ListAdapter<FavRecyclerModel, FavouriteAdapter.FavViewHolder>(FavDiffUtil()){

    lateinit var context: Context
    lateinit var binding: FavRowBinding


    class FavViewHolder(var binding :FavRowBinding): RecyclerView.ViewHolder(binding.root){
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavViewHolder {

        context=parent.context
        val inflater:LayoutInflater=parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding= FavRowBinding.inflate(inflater,parent,false)
        return FavViewHolder(binding)
    }



    override fun onBindViewHolder(holder: FavViewHolder, position: Int) {
        val currentObj=getItem(position)
        holder.binding.favCityName.text=currentObj.city
        holder.binding.deleteIcon.setOnClickListener {  listen.RemoveClick(currentObj);}
        holder.binding.rowFav.setOnClickListener {
            listen.onFavClick(currentObj.lat,currentObj.lon)
        }
    }

}
class FavDiffUtil: DiffUtil.ItemCallback<FavRecyclerModel>(){
    override fun areItemsTheSame(oldItem: FavRecyclerModel, newItem: FavRecyclerModel): Boolean {
        return oldItem.city==newItem.city
    }
    override fun areContentsTheSame(oldItem: FavRecyclerModel, newItem: FavRecyclerModel): Boolean {
        return oldItem==newItem
    }
}