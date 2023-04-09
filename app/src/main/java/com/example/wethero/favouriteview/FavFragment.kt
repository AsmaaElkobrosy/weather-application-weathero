package com.example.wethero.Model.favouriteview


import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wethero.Model.Repo
import com.example.wethero.R
import com.example.wethero.databinding.FragmentFavBinding
import com.example.wethero.favouriteModel.FavClickInterface
import com.example.wethero.favouriteModel.FavRecyclerModel
import com.example.wethero.favouritesViewModel.FavViewModel
import com.example.wethero.favouritesViewModel.FavViewModelFactory
import com.example.wethero.favouritesViewModel.FavouriteAdapter
import com.example.wethero.favouritesViewModel.MapsFragment
import com.example.wethero.favouriteview.DetailsFavFragment
import com.example.wethero.localdatabase.LocalSource
import com.example.wethero.network.RemoteSource
import com.example.wethero.view.HomeFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton


class FavFragment : Fragment() , FavClickInterface {

    lateinit var fab:FloatingActionButton
    lateinit var homeFragment: HomeFragment
    lateinit var userlist :ArrayList<FavRecyclerModel>
    lateinit var listener:FavClickInterface
    lateinit var binding: FragmentFavBinding
    lateinit var favViewModel: FavViewModel
    lateinit var favViewModelFactory: FavViewModelFactory
    lateinit var favAdapter: FavouriteAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavBinding.inflate(inflater, container, false)
        val root: View = binding.root
        favViewModelFactory= FavViewModelFactory(
            Repo.getInstance(RemoteSource.getINSTANCE(), LocalSource(requireContext()))
        )
        favViewModel= ViewModelProvider(this,favViewModelFactory).get(FavViewModel::class.java)

        favViewModel.getFavFromRoom()


        val bundle = arguments
        val lat = bundle?.getString("lat")
        val lon = bundle?.getString("lon")
        val city = bundle?.getString("city")
        println(lat+"   "+lon+" "+city)

//        if(checkConnection()){
//            if (lat != null && lon !=null) {
//                homeFragment.getWeather( lat.toDouble() ,lon.toDouble(),"ca2b01baf69d772e70734ccfdc4cb9cd")
//            }
//        }else{
        var favModel= FavRecyclerModel(lat.toString(),lon.toString(),city.toString())
        favAdapter= FavouriteAdapter(this)
        if(lat!= null && lon!= null) {
            favViewModel.insertFav(favModel)
        }
        binding.recyclerfav.apply {
            this.adapter=favAdapter
            layoutManager= LinearLayoutManager(context)
        }
        favViewModel.currentWeather.observe(viewLifecycleOwner){
            favAdapter.submitList(it)
            favAdapter.notifyDataSetChanged()
        }



        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fab = view.findViewById(R.id.fab)
        fab.setOnClickListener(View.OnClickListener {
            val mFragmentManager = requireActivity().supportFragmentManager
            val mFragmentTransaction = mFragmentManager.beginTransaction()
            val mFragment = MapsFragment()
            mFragmentTransaction.add(R.id.fav_fragment, mFragment).commit()
        })
    }
    fun checkConnection(): Boolean {
        val cm = context?.getSystemService(AppCompatActivity.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        return if (activeNetwork != null) {true}else{false}}

    override fun onFavClick(lat: String, lon: String) {
        val mFragmentManager = requireActivity().supportFragmentManager
        val mFragmentTransaction = mFragmentManager.beginTransaction()
        val mFragment = DetailsFavFragment()

        val mBundle = Bundle()
        mBundle.putString("lat",lat )
        mBundle.putString("lon",lon  )
        mFragment.arguments = mBundle
        mFragmentTransaction.add(R.id.fav_fragment, mFragment).commit()
    }

    override fun RemoveClick(favModel: FavRecyclerModel) {
//        val pList = favAdapter.currentList.toMutableList()
//        pList.remove(favModel)
//        favAdapter.submitList(pList)
        favViewModel.deleteFav(favModel)
        favAdapter.notifyDataSetChanged()
    }

//    override fun deleteFav(favModel: FavRecyclerModel) {
//       favViewModel.deleteFav(favModel)
//        favAdapter.notifyDataSetChanged()
//    }

}