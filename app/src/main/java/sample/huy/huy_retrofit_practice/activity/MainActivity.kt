package sample.huy.huy_retrofit_practice.activity.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import sample.huy.huy_retrofit_practice.R
import sample.huy.huy_retrofit_practice.activity.Network.DataService
import sample.huy.huy_retrofit_practice.activity.Network.RetrofitService
import sample.huy.huy_retrofit_practice.activity.model.AlbumPhoto
import sample.huy.huy_retrofit_practice.activity.model.DaggerCarComponent

class MainActivity : AppCompatActivity() {
    private var retrofitService = RetrofitService.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        var dataService = retrofitService.create(DataService::class.java)
        var call = dataService.getAlbumPhotos()
        call.enqueue(
            object : Callback<List<AlbumPhoto>> {
                @Override
                override fun onResponse(call: Call<List<AlbumPhoto>>, response: Response<List<AlbumPhoto>>) {
                    Log.d("Huy", " onResponse:" + response.body()?.size)
                }

                @Override
                override fun onFailure(call: Call<List<AlbumPhoto>>, t: Throwable) {

                }

            }
        )

        //test dagger
        //var carComponent
        var carComponent = DaggerCarComponent.create()
        var car = carComponent.getCar()
        car.drive()
    }
}
