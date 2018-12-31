package sample.huy.huy_retrofit_practice.UI.Fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import sample.huy.huy_retrofit_practice.Network.PostNetworkService
import sample.huy.huy_retrofit_practice.R
import sample.huy.huy_retrofit_practice.UI.Adapters.PostRecycleViewAdapter
import sample.huy.huy_retrofit_practice.activity.model.Post

class PostListFragment:Fragment() {
    private lateinit var postRecylerView: RecyclerView
    private lateinit var postRecyclerViewAdapter: PostRecycleViewAdapter
    private lateinit var postRecyclerViewManager: RecyclerView.LayoutManager
    private lateinit var postDataSet: ArrayList<Post>

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = layoutInflater.inflate(R.layout.post_list_view_fragment,
            container, false).apply { tag = TAG}
        postRecyclerViewManager = LinearLayoutManager(activity)
        postDataSet = ArrayList()
        postRecyclerViewAdapter = PostRecycleViewAdapter(postDataSet)
        postRecylerView = rootView.findViewById(R.id.recycleViewPost)
        postRecylerView.apply {
            setHasFixedSize(true)
            layoutManager = postRecyclerViewManager
            adapter = postRecyclerViewAdapter
        }
        PostNetworkService.getAllPost(postDataSet, postRecyclerViewAdapter)
        return rootView
    }
    companion object {
        val TAG = "Pikachu-PostListFragment"
    }
}