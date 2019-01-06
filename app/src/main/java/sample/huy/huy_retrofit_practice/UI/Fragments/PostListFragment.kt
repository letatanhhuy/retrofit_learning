package sample.huy.huy_retrofit_practice.UI.Fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.*
import android.widget.Toast
import sample.huy.huy_retrofit_practice.Network.PostNetworkService
import sample.huy.huy_retrofit_practice.R
import sample.huy.huy_retrofit_practice.UI.Adapters.PostRecycleViewAdapter
import sample.huy.huy_retrofit_practice.activity.model.Post

class PostListFragment:Fragment() {
    private lateinit var postRecylerView: RecyclerView
    private lateinit var postRecyclerViewAdapter: PostRecycleViewAdapter
    private lateinit var postRecyclerViewManager: RecyclerView.LayoutManager
    private lateinit var postDataSet: ArrayList<Post>

    private val mHandler: Handler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message?) {
            Log.d("Pikachu", " handleMessage:" + msg?.what)
            when(msg?.what) {
                PostNetworkService.RESULT.LIST_UPDATE_SUCCESS.INDEX -> {
                    postDataSet = msg?.obj as ArrayList<Post>
                    postRecyclerViewAdapter.notifyDataSetChanged()
                    //Toast.makeText(activity, "Update succeed", Toast.LENGTH_LONG).show()
                }
                PostNetworkService.RESULT.LIST_UPDATE_FAILED.INDEX -> {
                   Toast.makeText(activity, "Update failed", Toast.LENGTH_LONG).show()
                }
            }
            super.handleMessage(msg)
        }
    }

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
        PostNetworkService.getAllPost(postDataSet, mHandler)
        setHasOptionsMenu(true)
        return rootView
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        menu.findItem(R.id.menuCreateNew).isVisible = true
        menu.findItem(R.id.menuCreateOk).isVisible = false
    }

    companion object {
        val TAG = "Pikachu-PostListFragment"
    }
}