package sample.huy.huy_retrofit_practice.activity.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import sample.huy.huy_retrofit_practice.R
import sample.huy.huy_retrofit_practice.UI.PostRecycleViewAdapter
import sample.huy.huy_retrofit_practice.activity.Network.DataService
import sample.huy.huy_retrofit_practice.activity.Network.RetrofitService
import sample.huy.huy_retrofit_practice.activity.model.Post

class MainActivity : AppCompatActivity() {
    private var retrofitService = RetrofitService.getInstanceLocal()


    private lateinit var postRecylerView:RecyclerView
    private lateinit var postRecyclerViewAdapter: PostRecycleViewAdapter
    private lateinit var postRecyclerViewManager: RecyclerView.LayoutManager
    private lateinit var postDataSet: ArrayList<Post>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        postRecyclerViewManager = LinearLayoutManager(this)
        postDataSet = ArrayList()

        postRecyclerViewAdapter = PostRecycleViewAdapter(postDataSet)
        postRecylerView = recycleViewPost.apply {
            setHasFixedSize(true)
            layoutManager = postRecyclerViewManager
            adapter = postRecyclerViewAdapter
        }
    }

    override fun onStart() {
        super.onStart()
        var dataService = retrofitService.create(DataService::class.java)
        var callGetAllPost = dataService.getAllPost()
        callGetAllPost.enqueue(
            object : Callback<List<Post>> {
                @Override
                override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                    postDataSet.clear()
                    var size = response.body()?.size ?: 0
                    for (i in 0..size-1) {
                        var post:Post = response.body()?.get(i) as Post
                        postDataSet.add(post)
                    }
                    postRecyclerViewAdapter.notifyDataSetChanged()
                }

                @Override
                override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                    Log.d("Pikachu", " get onFailure:" + t.message)
                }

            }
        )


//        var testPost = Post(8, "Post number 10")
//        var callcreate = dataService.createPost(testPost)
//        callcreate.enqueue(
//            object : Callback<Post> {
//                override fun onResponse(call: Call<Post>, response: Response<Post>) {
//                    Log.d("Pikachu", " create:" + response.body()?.id)
//                }
//                override fun onFailure(call: Call<Post>, t: Throwable) {
//                    Log.d("Pikachu", " onFailure:" + t.message)
//                }
//            }
//        )
//
//        //test dagger
//        //var carComponent
//        var carComponent = DaggerCarComponent.create()
//        var car = carComponent.getCar()
//        car.drive()


    }
}
