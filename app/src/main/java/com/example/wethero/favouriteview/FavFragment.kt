package com.example.wethero.favouriteview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.wethero.R
import com.google.android.material.floatingactionbutton.FloatingActionButton


class FavFragment : Fragment() {
 lateinit var fab:FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fab.setOnClickListener{
            findNavController().navigate(
                R.id.action_favFragment_to_mapsFragment
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fab = requireView().findViewById(R.id.fab)

        return inflater.inflate(R.layout.fragment_fav, container, false)
    }


}