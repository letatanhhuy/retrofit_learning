package sample.huy.huy_retrofit_practice.Network

import android.util.Log
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import sample.huy.huy_retrofit_practice.R
import sample.huy.huy_retrofit_practice.UI.Adapters.PostRecycleViewAdapter
import sample.huy.huy_retrofit_practice.UI.Fragments.CreatePostFragment
import sample.huy.huy_retrofit_practice.UI.Fragments.PostListFragment
import sample.huy.huy_retrofit_practice.activity.Network.DataService
import sample.huy.huy_retrofit_practice.activity.Network.RetrofitService
import sample.huy.huy_retrofit_practice.activity.model.Post

class PostNetworkService {
    companion object {
        fun getAllPost(list:ArrayList<Post>, adapter: PostRecycleViewAdapter) {
            var dataService = RetrofitService.getInstanceLocal().create(DataService::class.java)
            var callGetAllPost = dataService.getAllPost()
            callGetAllPost.enqueue(
                object : Callback<List<Post>> {
                    @Override
                    override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                        var size = response.body()?.size ?: 0
                        list.clear()
                        for (i in 0 until size) {
                            var post:Post = response.body()?.get(i) as Post
                            list.add(post)
                        }
                        adapter.notifyDataSetChanged()
                    }

                    @Override
                    override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                        Log.e("Pikachu", " get onFailure:" + t.message)
                    }

                }
            )
        }

        fun createNewPost(post:Post, fragment: CreatePostFragment): Post? {
            var retVal:Post? = null
            var dataService = RetrofitService.getInstanceLocal().create(DataService::class.java)
            var callCreate = dataService.createPost(post)
            callCreate.enqueue (
                object : Callback<Post> {
                    override fun onResponse(call: Call<Post>, response: Response<Post>) {
                        Log.d("Pikachu", " create onResponse:" + response.body().toString())
                        retVal = response.body()
                        if(retVal != null) {
                            fragment.fragmentManager?.beginTransaction()
                                ?.replace(R.id.mainViewFrame, PostListFragment())?.commit()
                            Toast.makeText(fragment.context, "Create done", Toast.LENGTH_LONG).show()
                        } else {
                            Toast.makeText(fragment.context, "Create failed", Toast.LENGTH_LONG).show()
                        }
                    }
                    override fun onFailure(call: Call<Post>, t: Throwable) {
                        Log.e("Pikachu", " create onFailure:" + t.message)
                    }
                }
            )
            return retVal
        }
    }
}