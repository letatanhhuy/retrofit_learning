package sample.huy.huy_retrofit_practice.activity.Network

import retrofit2.Call
import retrofit2.http.*
import sample.huy.huy_retrofit_practice.activity.model.Post

interface DataService {
    //@GET("{id}/{repo}/posts")
    //fun getAllPost(@Path("id") id:String, @Path("repo") repo:String):Call<List<Post>>

    @GET("/posts")
    fun getAllPost():Call<List<Post>>

    @POST("/posts")
    fun createPost(@Body post:Post):Call<Post>

    @DELETE("/posts/{id}")
    fun deletePost(@Path("id") id:Int):Call<Void>
}