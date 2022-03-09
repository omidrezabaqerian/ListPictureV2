package ir.omidrezabagherian.listpicturesv2.ui.home

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

class HomeViewModel : ViewModel() {

    private val _homeResponse = MutableLiveData<List<Photo>>()
    val homeResponse: LiveData<List<Photo>> = _homeResponse

    fun showInfo() {
        NetworkManager.service.showImage(
            "1c04e05bce6e626247758d120b372a73",
            "flickr.photos.getPopular",
            "34427466731@N01",
            "url_s",
            "json",
            1,
            30,
            1
        ).enqueue(
            object : Callback<ImageHome> {
                override fun onResponse(call: Call<ImageHome>, response: Response<ImageHome>) {
                    Log.i("Response", response.body()!!.photos.photo.toString())
                    _homeResponse.value = response.body()!!.photos.photo
                }

                override fun onFailure(call: Call<ImageHome>, t: Throwable) {
                    Log.i("Failure", t.toString())
                }

            }
        )
    }

}