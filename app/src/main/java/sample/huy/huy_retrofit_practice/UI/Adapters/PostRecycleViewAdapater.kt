package sample.huy.huy_retrofit_practice.UI.Adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import kotlinx.android.synthetic.main.post_row_view.view.*
import sample.huy.huy_retrofit_practice.R
import sample.huy.huy_retrofit_practice.activity.model.Post

class PostRecycleViewAdapter(private val dataSet: ArrayList<Post>) : RecyclerView.Adapter<PostRecycleViewAdapter.PostViewHolder>() {

    class PostViewHolder(val postRowView: ViewGroup) : RecyclerView.ViewHolder(postRowView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val postRowView = LayoutInflater.from(parent.context).inflate(R.layout.post_row_view, parent, false) as ViewGroup
        return PostViewHolder(postRowView)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
       holder.postRowView.txtPostName.text = dataSet.get(position).title
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}