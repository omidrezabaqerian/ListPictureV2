package ir.omidrezabagherian.listpicturesv2.ui.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ir.omidrezabagherian.testapplicationfive.Data.NetworkManager
import ir.omidrezabagherian.testapplicationfive.ModelHome.ImageHome
import ir.omidrezabagherian.testapplicationfive.ModelHome.Photo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchViewModel : ViewModel() {

    private val _searchResponse = MutableLiveData<List<Photo>>()
    val searchResponse: LiveData<List<Photo>> = _searchResponse

    fun searchImages(query:String) {
        NetworkManager.service.searchImage(
            "1c04e05bce6e626247758d120b372a73",
            "flickr.photos.search",
            query,
            "34427466731@N01",
            "url_s",
            "json",
            1,
            30
        ).enqueue(object : Callback<ImageHome> {
            override fun onResponse(call: Call<ImageHome>, response: Response<ImageHome>) {
                Log.i("Response", response.body()!!.photos.photo.toString())
                _searchResponse.value = response.body()!!.photos.photo
            }

            override fun onFailure(call: Call<ImageHome>, t: Throwable) {
                Log.i("Failure", t.toString())
            }

        })
    }

}