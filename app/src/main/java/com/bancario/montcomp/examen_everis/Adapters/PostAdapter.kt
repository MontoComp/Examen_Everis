package com.bancario.montcomp.examen_everis.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.bancario.montcomp.examen_everis.Network.PostReponse
import com.bancario.montcomp.examen_everis.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.content_main.view.*
import kotlinx.android.synthetic.main.item_user_post.view.*

class PostAdapter(private var data: List<PostReponse>, private val listener: PostHolder.OnAdapterListener) :
    RecyclerView.Adapter<PostAdapter.PostHolder>() {


    class PostHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {

        init {
            v.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            if (v != null) {
                Toast.makeText(v.context, "Item", Toast.LENGTH_SHORT).show()
            }
        }

        //
        interface OnAdapterListener {
            fun onItemClickListener( item: PostReponse)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostHolder {
        val inflatedView = parent.inflate(R.layout.item_user_post, false)
        return PostHolder(inflatedView)
    }
    private fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
        return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
    }


    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: PostHolder, position: Int) {
        val post: PostReponse = this.data[position]

        if(!post.user_image.isBlank()) {
            Picasso.get()
                .load(post.user_image)
                .into(holder.itemView.iv_userimage_post)
        }
        holder.itemView.tv_username_post.text = post.username
        if(!post.image.isBlank()) {
            Picasso.get()
                .load(post.image)
                .into(holder.itemView.iv_image_post)
        }
        holder.itemView.tv_body_post.text = post.body
        holder.itemView.tv_likes_post.text = post.likes.toString()




        holder.itemView.setOnClickListener { listener.onItemClickListener(post) }
    }



    fun updateList(postList: List<PostReponse>) {
        this.data = postList
        this.notifyDataSetChanged()
    }
}