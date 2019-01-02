package sample.huy.huy_retrofit_practice.UI.Fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.support.v4.app.Fragment
import android.util.Log
import android.view.*
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import sample.huy.huy_retrofit_practice.Network.PostNetworkService
import sample.huy.huy_retrofit_practice.R
import sample.huy.huy_retrofit_practice.activity.model.Post

class CreatePostFragment:Fragment() {
    private val mHandler: Handler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message?) {
            Log.d(TAG, " handleMessage:" + msg?.what)
            when(msg?.what) {
                PostNetworkService.RESULT.POST_CREATE_SUCCESS.INDEX -> {
                    Toast.makeText(activity, "Create succeed", Toast.LENGTH_LONG).show()
                    fragmentManager?.beginTransaction()?.replace(R.id.mainViewFrame, PostListFragment())?.commit()
                }
                PostNetworkService.RESULT.POST_CREATE_FAIL.INDEX -> {
                    Toast.makeText(activity, "Create failed", Toast.LENGTH_LONG).show()
                }
            }
            super.handleMessage(msg)
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var rootView = layoutInflater.inflate(R.layout.post_create_fragment, container, false).apply { tag = TAG }
        var textPostName:TextView = rootView.findViewById(R.id.txePostName)
        var textPostId:TextView = rootView.findViewById(R.id.txePostId)
        var buttonCreate:Button = rootView.findViewById(R.id.btnCreate)
        buttonCreate.setOnClickListener {
            PostNetworkService.createNewPost(Post(Integer.valueOf(textPostId.text.toString()), textPostName.text.toString()), mHandler)
        }
        return rootView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        menu.clear()
        //menu.findItem(R.id.menuCreateNew).isVisible = false
    }

    companion object {
        val TAG = "Pikachu-CreatePostFragment"
    }
}