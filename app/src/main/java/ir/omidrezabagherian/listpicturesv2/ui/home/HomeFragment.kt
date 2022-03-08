package ir.omidrezabagherian.listpicturesv2.ui.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import ir.omidrezabagherian.listpicturesv2.R
import ir.omidrezabagherian.listpicturesv2.databinding.FragmentHomeBinding
import ir.omidrezabagherian.listpicturesv2.ui.adapter.ImageAdapter
import ir.omidrezabagherian.testapplicationfive.Data.NetworkManager
import ir.omidrezabagherian.testapplicationfive.ModelHome.ImageHome
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var homeBinding: FragmentHomeBinding
    private lateinit var imageAdapter: ImageAdapter
    private val homeViewModel: HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeBinding = FragmentHomeBinding.bind(view)

        homeBinding.recyclerviewHome.layoutManager = LinearLayoutManager(context)

        imageAdapter = ImageAdapter(requireContext())

        homeBinding.recyclerviewHome.adapter = imageAdapter

        homeViewModel.showInfo()

        homeViewModel.homeResponse.observe(viewLifecycleOwner, Observer {
            imageAdapter.setDataList(it)
        })

    }
}