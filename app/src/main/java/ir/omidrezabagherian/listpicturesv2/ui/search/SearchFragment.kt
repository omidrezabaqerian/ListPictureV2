package ir.omidrezabagherian.listpicturesv2.ui.search

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import ir.omidrezabagherian.listpicturesv2.R
import ir.omidrezabagherian.listpicturesv2.databinding.FragmentSearchBinding
import ir.omidrezabagherian.listpicturesv2.ui.adapter.ImageAdapter
import ir.omidrezabagherian.listpicturesv2.ui.home.HomeViewModel
import ir.omidrezabagherian.testapplicationfive.Data.NetworkManager
import ir.omidrezabagherian.testapplicationfive.ModelHome.ImageHome
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchFragment : Fragment(R.layout.fragment_search) {

    private lateinit var searchBinding: FragmentSearchBinding
    private lateinit var imageAdapter: ImageAdapter
    private val searchViewModel: SearchViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        searchBinding = FragmentSearchBinding.bind(view)

        searchBinding.recyclerviewSearch.layoutManager = LinearLayoutManager(context)

        imageAdapter = ImageAdapter(requireContext())



        searchBinding.searchviewSearch.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {

                searchBinding.recyclerviewSearch.adapter = imageAdapter

                searchViewModel.searchImages(query.toString())

                searchViewModel.searchResponse.observe(viewLifecycleOwner, Observer {
                    imageAdapter.setDataList(it)
                })

                return true
            }

            override fun onQueryTextChange(error: String?): Boolean {
                Toast.makeText(
                    requireContext(),
                    "Please enter word in search box",
                    Toast.LENGTH_SHORT
                ).show()
                return false
            }

        })

        super.onViewCreated(view, savedInstanceState)
    }
}