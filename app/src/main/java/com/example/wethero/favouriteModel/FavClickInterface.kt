package com.example.wethero.favouriteModel

interface FavClickInterface {

    fun onFavClick( lat :String, lon:String)

    fun RemoveClick(favModel: FavRecyclerModel)
}