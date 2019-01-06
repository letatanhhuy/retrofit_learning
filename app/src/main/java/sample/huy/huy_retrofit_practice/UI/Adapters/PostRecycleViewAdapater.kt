package sample.huy.huy_retrofit_practice.UI.Adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.post_row_view.view.*
import sample.huy.huy_retrofit_practice.R
import sample.huy.huy_retrofit_practice.activity.model.Post



class PostRecycleViewAdapter(private val dataSet: ArrayList<Post>, private val postItemListener: PostItemListener) : RecyclerView.Adapter<PostRecycleViewAdapter.PostViewHolder>() {

    class PostViewHolder(val postRowView: ViewGroup) : RecyclerView.ViewHolder(postRowView)

    private lateinit var context:Context

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
            postItemListener.onDeletePost(dataSet[position])
        }
        holder.postRowView.checkboxPost.setOnCheckedChangeListener { buttonView, checked ->
            if (checked) {
                postItemListener.onCheck(dataSet[position])
            } else {
                postItemListener.onUncheck(dataSet[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    interface PostItemListener {
        fun onCheck(post: Post)
        fun onUncheck(post: Post)
        fun onDeletePost(post: Post)
    }
}