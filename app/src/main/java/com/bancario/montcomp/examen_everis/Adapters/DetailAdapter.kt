package com.bancario.montcomp.examen_everis.Adapters

import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bancario.montcomp.examen_everis.Network.Comment

class DetailAdapter(private var data: List<Comment>) {

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
            fun onItemClickListener( item: Comment)
        }

    }
}