package sample.huy.huy_retrofit_practice.UI.Adapters

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.post_row_view.view.*
import sample.huy.huy_retrofit_practice.Network.PostNetworkService
import sample.huy.huy_retrofit_practice.R
import sample.huy.huy_retrofit_practice.activity.model.Post

class PostRecycleViewAdapter(private val dataSet: ArrayList<Post>) : RecyclerView.Adapter<PostRecycleViewAdapter.PostViewHolder>() {
    class PostViewHolder(val postRowView: ViewGroup) : RecyclerView.ViewHolder(postRowView)
    private lateinit var context:Context
    private var mHandler:Handler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message?) {
            Log.d("Pikachu", " handleMessage:" + msg?.what)
            when(msg?.what) {
                PostNetworkService.RESULT.POST_DELETE_SUCCESS.INDEX -> {
                    dataSet.remove(msg.obj)
                    notifyDataSetChanged()
                }
                PostNetworkService.RESULT.POST_DELETE_FAIL.INDEX -> {

                }
            }
            super.handleMessage(msg)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        context = parent.context
        val postRowView = LayoutInflater.from(parent.context).inflate(R.layout.post_row_view, parent, false) as ViewGroup
        return PostViewHolder(postRowView)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.postRowView.txtPostName.text = dataSet[position].title
        holder.postRowView.setOnClickListener {
            Toast.makeText(context, "test" + dataSet[position].id, Toast.LENGTH_LONG).show()
        }
        holder.postRowView.btnDelete.setOnClickListener {
            PostNetworkService.deletePost(dataSet[position], mHandler)
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}