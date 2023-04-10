//package com.example.wethero.localdatabase
//
//
//import com.example.wethero.Model.Welcome
//import com.example.wethero.favouriteModel.FavRecyclerModel
//import kotlinx.coroutines.flow.Flow
//
//class FakeLocalSource (var favs: MutableList<Welcome>? = mutableListOf<Welcome>()):LocalSourceInterface {
//    override suspend fun insert(welcome: Welcome) {
//        favs?.add(welcome)
//    }
//
//    override fun getAllStored(): Flow=<Welcome>  {
//        favs?.let { emit(it) }
//    }
//
//    override fun getAllFav(): Flow<List<FavRecyclerModel>> {
////        favs?.let { emit(it) }
//    }
//
//    override suspend fun insertFav(favModel: FavRecyclerModel) {
//        TODO("Not yet implemented")
//    }
//
//    override suspend fun deleteFav(favModel: FavRecyclerModel): Int {
//        TODO("Not yet implemented")
//    }
//
//}