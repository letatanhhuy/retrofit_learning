package sample.huy.huy_retrofit_practice.activity.Network

import retrofit2.Call
import retrofit2.http.GET
import sample.huy.huy_retrofit_practice.activity.model.AlbumPhoto

interface DataService {
    @GET("/photos")
    fun getAlbumPhotos():Call<List<AlbumPhoto>>
}