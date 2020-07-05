package com.bancario.montcomp.examen_everis

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bancario.montcomp.appkotlin_v1.Network.Repository
import com.bancario.montcomp.examen_everis.Adapters.PostAdapter
import com.bancario.montcomp.examen_everis.Network.PostReponse
import com.google.gson.Gson

import kotlinx.android.synthetic.main.activity_list_user.*
import kotlinx.android.synthetic.main.content_list_user.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class ListUserActivity : AppCompatActivity(), PostAdapter.PostHolder.OnAdapterListener {

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: PostAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_user)
        setSupportActionBar(toolbar)

        adapter = PostAdapter(ArrayList(),this)
        linearLayoutManager = LinearLayoutManager(this)
        rv_post.layoutManager= linearLayoutManager
        rv_post.adapter = adapter

        callService()

        //pb_loading.isIndeterminate=true
        //pb_loading.animate()

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    private  fun callService(){


        val service = Repository.RetrofitRepository.getService()
        GlobalScope.launch(Dispatchers.IO){
            val response = service.getPosts()
            //val mResponse = service.savePost("aaa","sdasd","dsadas")//

            Log.d("Estado Actual",response.body().toString())

            withContext(Dispatchers.Main){
                //Actualizar Interfaz
                //pb_loading.visibility= View.INVISIBLE
                try {
                    if(response.isSuccessful){
                        val posts:List<PostReponse>?= response.body()

                        if( posts!= null){


                            /*for(j in 0 until posts.size){
                                val sublist = posts[j].comment

                                for (i in 0 until sublist.size) {

                                    var jsonusername =sublist[i].username
                                    /*var jsontitle =
                                    JSONObject(mylistArray.getString(i)).get("title").toString()
                                var jsonmydescription =
                                    JSONObject(mylistArray.getString(i)).get("description")
                                        .toString()*/
                                    Toast.makeText(
                                        this@ListUserActivity,
                                        "El Usuario ${posts[j].username} tiene dentro de sus comentarios a :${jsonusername}",
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                            }*/
                            //pb_loading.invalidate()
                            //pb_loading.visibility = View.GONE
                            adapter.updateList(posts)

                            /*Glide.with(this@MainActivity).load(user.image).centerCrop().into(iv_profile)

                            tv_fullname.text="${user.name} ${user.lastname}"

                            tv_likes.text="${user.social.likes}"
                            tv_posts.text="${user.social.posts}"
                            tv_shares.text="${user.social.shares}"
                            tv_friends.text="${user.social.friends}"

                            tv_age.text="${user.age}"
                            tv_mail.text="${user.email}"
                            tv_location.text="${user.location}"
                            tv_work.text="${user.occupation}"*/

                            Toast.makeText(this@ListUserActivity,"${posts[0].comment}", Toast.LENGTH_LONG).show()
                        }
                    }else{
                        Toast.makeText(this@ListUserActivity,"Error ${response.code()}", Toast.LENGTH_LONG).show()
                    }
                }catch (e: HttpException){
                    Toast.makeText(this@ListUserActivity,"Error ${e.message()}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    override fun onItemClickListener(item: PostReponse) {
        Toast.makeText(this, "Click item ${item.username}", Toast.LENGTH_LONG).show()


        /**
         * puedes enviar los extras a una pantalla de detalle
         */
        val postString : String = Gson().toJson(item, PostReponse::class.java)
        Log.d("GSON Class to String", postString )
        val intent= Intent(this@ListUserActivity,DetailActivity::class.java)
        intent.putExtra("mydetail",postString)
        startActivity(intent)
        //finish()

        /*
        * esto lo jalas donde necesites convertirtlo
        * */

        val post : PostReponse = Gson().fromJson(postString, PostReponse::class.java)
        Log.d("GSON string to class", post.username )


    }

}

