package com.bancario.montcomp.examen_everis

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bancario.montcomp.examen_everis.Adapters.DetailAdapter
import com.bancario.montcomp.examen_everis.Network.Comment
import com.bancario.montcomp.examen_everis.Network.PostReponse
import com.google.gson.Gson

import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.content_detail.*

class DetailActivity : AppCompatActivity() {

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: DetailAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setSupportActionBar(toolbar)

        adapter = DetailAdapter(ArrayList())
        linearLayoutManager = LinearLayoutManager(this)
        rv_misdetalles.layoutManager= linearLayoutManager
        rv_misdetalles.adapter = adapter


        var rec_detail= intent.getStringExtra("mydetail")

        val post : PostReponse = Gson().fromJson(rec_detail, PostReponse::class.java)
        //
        val sublist :List<Comment> ? = post.comment

        if( sublist!= null){
            adapter.updateListDetail(sublist)
        }

        /*for (i in 0 until sublist.size) {

            var jsonusername =sublist[i].username

            Toast.makeText(
                this,
                "El Usuario ${post.username} tiene dentro de sus comentarios a :${jsonusername}",
                Toast.LENGTH_LONG
            ).show()
        }*/




    }

}
