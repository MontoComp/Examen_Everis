package com.bancario.montcomp.examen_everis

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bancario.montcomp.appkotlin_v1.Network.Repository
import com.bancario.montcomp.appkotlin_v1.Network.UserResponse
import com.bancario.montcomp.examen_everis.Adapters.AmigoAdapter
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_amigos.*
import kotlinx.android.synthetic.main.content_amigos.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException


class AmigosActivity : AppCompatActivity() , AmigoAdapter.AmigoHolder.OnAdapterListener {

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: AmigoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_amigos)
        setSupportActionBar(toolbar)

        adapter = AmigoAdapter(ArrayList(),this)
        linearLayoutManager = LinearLayoutManager(this)
        rv_misamigos.layoutManager= linearLayoutManager
        rv_misamigos.adapter = adapter

        callService()

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    //
    private  fun callService(){

        val service = Repository.RetrofitRepository.getService()
        GlobalScope.launch(Dispatchers.IO){
            val response = service.getFriends()
            //val mResponse = service.savePost("aaa","sdasd","dsadas")//

            Log.d("Estado Actual",response.body().toString())

            withContext(Dispatchers.Main){
                //Actualizar Interfaz
                //pb_loading.visibility= View.INVISIBLE
                try {
                    if(response.isSuccessful){
                        val users:List<UserResponse>?= response.body()

                        if( users!= null){


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
                            adapter.updateListAmigo(users)


                            //Toast.makeText(this@AmigosActivity,"${users[0].username}", Toast.LENGTH_LONG).show()
                        }
                    }else{
                        Toast.makeText(this@AmigosActivity,"Error ${response.code()}", Toast.LENGTH_LONG).show()
                    }
                }catch (e: HttpException){
                    Toast.makeText(this@AmigosActivity,"Error ${e.message()}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    override fun onItemClickListener(item: UserResponse,view:View) {

        //val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "${item.phone}"))
        //startActivity(intent)

        Snackbar.make(view, "Para comunicarte con  ${item.name}  puedes llamar al  ${item.phone} ", Snackbar.LENGTH_LONG)
            .setAction("Action", null).show()


        val userString : String = Gson().toJson(item, UserResponse::class.java)
        Log.d("GSON Class to String", userString )


        val user : UserResponse = Gson().fromJson(userString, UserResponse::class.java)
        Log.d("GSON string to class", user.username )


    }

}
