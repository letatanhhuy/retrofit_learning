package sample.huy.huy_retrofit_practice.Network

import android.util.Log
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import sample.huy.huy_retrofit_practice.R
import sample.huy.huy_retrofit_practice.UI.Fragments.CreatePostFragment
import sample.huy.huy_retrofit_practice.UI.Fragments.PostListFragment
import sample.huy.huy_retrofit_practice.activity.Network.DataService
import sample.huy.huy_retrofit_practice.activity.Network.RetrofitService
import sample.huy.huy_retrofit_practice.activity.model.Post
import android.os.Handler

class PostNetworkService {
    enum class RESULT (val INDEX: Int){
        LIST_UPDATE_SUCCESS(0),
        LIST_UPDATE_FAILED(1),
        POST_CREATE_SUCCESS(2),
        POST_CREATE_FAIL(3),
        POST_DELETE_SUCCESS(4),
        POST_DELETE_FAIL(5)
    }
    companion object {
        fun getAllPost(list:ArrayList<Post>, handler: Handler) {
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

                        val msg = handler.obtainMessage()
                        msg.what = RESULT.LIST_UPDATE_SUCCESS.INDEX
                        msg.obj = list
                        handler.sendMessage(msg)
                    }

                    @Override
                    override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                        Log.e("Pikachu", " get onFailure:" + t.message)
                        val msg = handler.obtainMessage()
                        msg.what = RESULT.LIST_UPDATE_FAILED.INDEX
                        handler.sendMessage(msg)
                    }

                }
            )
        }

        fun createNewPost(post:Post, handler: Handler) {

            var dataService = RetrofitService.getInstanceLocal().create(DataService::class.java)
            var callCreate = dataService.createPost(post)
            callCreate.enqueue (
                object : Callback<Post> {
                    override fun onResponse(call: Call<Post>, response: Response<Post>) {
                        Log.d("Pikachu", " create onResponse:" + response.body().toString())
                        var retVal = response.body()
                        val msg = handler.obtainMessage()
                        if(retVal != null) {
                            msg.what = RESULT.POST_CREATE_SUCCESS.INDEX
                            msg.obj = retVal
                        } else {
                            msg.what = RESULT.POST_CREATE_FAIL.INDEX
                        }
                        handler.sendMessage(msg)
                    }
                    override fun onFailure(call: Call<Post>, t: Throwable) {
                        Log.e("Pikachu", " create onFailure:" + t.message)
                        val msg = handler.obtainMessage()
                        msg.what = RESULT.POST_CREATE_FAIL.INDEX
                        handler.sendMessage(msg)
                    }
                }
            )
        }

        fun deletePost(post: Post, handler: Handler) {
            var dataService = RetrofitService.getInstanceLocal().create(DataService::class.java)
            var callDelete = dataService.deletePost(post.id)
            callDelete.enqueue(
                object : Callback<Void> {
                    override fun onResponse(call: Call<Void>, response: Response<Void>) {
                        Log.d("Pikachu", " delete onResponse:")
                        val msg = handler.obtainMessage()
                        msg.what = RESULT.POST_DELETE_SUCCESS.INDEX
                        msg.obj = post
                        handler.sendMessage(msg)
                    }

                    override fun onFailure(call: Call<Void>, t: Throwable) {
                        Log.d("Pikachu", " delete onFailure:" + t.message)
                        val msg = handler.obtainMessage()
                        msg.what = RESULT.POST_DELETE_FAIL.INDEX
                        handler.sendMessage(msg)
                    }
                }
            )
        }
    }
}