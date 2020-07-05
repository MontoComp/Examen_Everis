package com.bancario.montcomp.examen_everis.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.bancario.montcomp.appkotlin_v1.Network.UserResponse
import com.bancario.montcomp.examen_everis.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_amigo_post.view.*

class AmigoAdapter(private var data:List<UserResponse>, private val listener: AmigoHolder.OnAdapterListener) :
    RecyclerView.Adapter<AmigoAdapter.AmigoHolder>() {

    class AmigoHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {

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
            fun onItemClickListener( item: UserResponse,view: View)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AmigoHolder {
        val inflatedView = parent.inflate(R.layout.item_amigo_post, false)
        return AmigoHolder(inflatedView)
    }
    private fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
        return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
    }



    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: AmigoHolder, position: Int) {
        val user: UserResponse = this.data[position]

        if(!user.image.isBlank()) {
            Picasso.get()
                .load(user.image)
                .into(holder.itemView.iv_amigo_list)
        }
        holder.itemView.tv_nombreamigo.text = user.name



        holder.itemView.setOnClickListener { listener.onItemClickListener(user,holder.itemView) }
    }


    fun updateListAmigo(userList: List<UserResponse>) {
        this.data = userList
        this.notifyDataSetChanged()
    }
}