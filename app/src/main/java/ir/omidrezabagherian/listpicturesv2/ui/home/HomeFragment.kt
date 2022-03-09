package ir.omidrezabagherian.listpicturesv2.ui.home

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ir.omidrezabagherian.listpicturesv2.R
import ir.omidrezabagherian.listpicturesv2.databinding.FragmentHomeBinding
import ir.omidrezabagherian.listpicturesv2.ui.adapter.ImageAdapter

class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var homeBinding: FragmentHomeBinding
    private lateinit var imageAdapter: ImageAdapter
    lateinit var layoutManager: LinearLayoutManager
    private val homeViewModel: HomeViewModel by viewModels()

    var page = 1
    var loading = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeBinding = FragmentHomeBinding.bind(view)

        layoutManager = LinearLayoutManager(context)

        homeBinding.recyclerviewHome.layoutManager = layoutManager

        homeBinding.progressbarHome.visibility = View.GONE

        imageAdapter = ImageAdapter(requireContext())

        homeBinding.recyclerviewHome.adapter = imageAdapter

        homeViewModel.showInfo(1)

        homeViewModel.homeResponse.observe(viewLifecycleOwner, Observer {
            imageAdapter.setDataList(it)
        })

        homeBinding.recyclerviewHome.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {

                if (dy > 0){
                    val visibleItemCount = layoutManager.childCount
                    val postVisibleItem = layoutManager.findFirstCompletelyVisibleItemPosition()

                    val total = imageAdapter.itemCount

                    if (!loading){
                        if ((visibleItemCount + postVisibleItem)>=total){

                            page++
                            loading = true
                            homeBinding.progressbarHome.visibility = View.VISIBLE

                            Handler(Looper.getMainLooper()).postDelayed({
                                if (::imageAdapter.isInitialized) {
                                    homeViewModel.showInfo(page)

                                    homeViewModel.homeResponse.observe(viewLifecycleOwner, Observer {
                                        imageAdapter.addDataList(it)
                                    })
                                }
                            }, 5000)
                        }
                    }

                }

                super.onScrolled(recyclerView, dx, dy)
            }
        })

    }

}