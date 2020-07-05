package com.bancario.montcomp.examen_everis

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.bancario.montcomp.examen_everis.Network.PostReponse
import com.google.gson.Gson

import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.content_detail.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setSupportActionBar(toolbar)

        var rec_detail= intent.getStringExtra("mydetail")

        val post : PostReponse = Gson().fromJson(rec_detail, PostReponse::class.java)
        Log.d("GSON string to class", post.username )


        val sublist = post.comment

        for (i in 0 until sublist.size) {

            var jsonusername =sublist[i].username
            /*var jsontitle =
            JSONObject(mylistArray.getString(i)).get("title").toString()
        var jsonmydescription =
            JSONObject(mylistArray.getString(i)).get("description")
                .toString()*/
            Toast.makeText(
                this,
                "El Usuario ${post.username} tiene dentro de sus comentarios a :${jsonusername}",
                Toast.LENGTH_LONG
            ).show()
        }



        //tv_comment.text="${post.username}"

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

}
