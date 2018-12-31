package sample.huy.huy_retrofit_practice.UI.Fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.post_create_fragment.*
import sample.huy.huy_retrofit_practice.Network.PostNetworkService
import sample.huy.huy_retrofit_practice.R
import sample.huy.huy_retrofit_practice.activity.model.Post

class CreatePostFragment:Fragment() {

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
            PostNetworkService.createNewPost(Post(Integer.valueOf(textPostId.text.toString()), textPostName.text.toString()), this)
        }
        return rootView
    }

    companion object {
        val TAG = "Pikachu-CreatePostFragment"
    }
}