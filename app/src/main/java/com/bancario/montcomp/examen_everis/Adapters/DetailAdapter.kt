package com.bancario.montcomp.examen_everis.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.bancario.montcomp.examen_everis.Network.Comment
import com.bancario.montcomp.examen_everis.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_detail.view.*

class DetailAdapter(private var data: List<Comment>) : RecyclerView.Adapter<DetailAdapter.DetailHolder>(){

    class DetailHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {

        init {
            v.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            if (v != null) {
                Toast.makeText(v.context, "Item", Toast.LENGTH_SHORT).show()
            }
        }



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailHolder {
        val inflatedView = parent.inflate(R.layout.item_detail, false)
        return DetailHolder(inflatedView)
    }
    private fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
        return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
    }


    override fun getItemCount(): Int {
        return  data.size
    }

    override fun onBindViewHolder(holder: DetailHolder, position: Int) {
        val comment: Comment = this.data[position]

        if(!comment.user_image.isBlank()) {
            Picasso.get()
                .load(comment.user_image)
                .into(holder.itemView.iv_detalle_foto)
        }
        holder.itemView.tv_detalle_comentario.text = comment.comment


    }


    fun updateListDetail(commentList: List<Comment>) {
        this.data = commentList
        this.notifyDataSetChanged()
    }
}