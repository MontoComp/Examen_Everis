package com.bancario.montcomp.examen_everis

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.bancario.montcomp.appkotlin_v1.Network.Repository
import com.bancario.montcomp.appkotlin_v1.Network.UserResponse
import com.bumptech.glide.Glide

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        callService()

        tv_irfeed.setOnClickListener{
            val intent = Intent(this@MainActivity,ListUserActivity::class.java)
            startActivity(intent)
        }
        tv_iramigos.setOnClickListener{
            val intent = Intent(this@MainActivity,AmigosActivity::class.java)
            startActivity(intent)
        }

        /*fab.setOnClickListener { view ->

            val intent = Intent(this@MainActivity,ListUserActivity::class.java)
            startActivity(intent)
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }*/
    }


    private  fun callService(){
        //progressbar.visibility =GONE
        val service = Repository.RetrofitRepository.getService()
        CoroutineScope(Dispatchers.IO).launch {
            val response = service.getprofile()

            withContext(Dispatchers.Main){
                //Actualizar Interfaz
                //pb_loading.visibility= View.INVISIBLE
                try {
                    if(response.isSuccessful){
                        val user: UserResponse?= response.body()
                        if( user!= null){
                            Glide.with(this@MainActivity).load(user.image).centerCrop().into(iv_profile)

                            tv_fullname.text="${user.name} ${user.lastname}"

                            tv_likes.text="${user.social.likes}"
                            tv_posts.text="${user.social.posts}"
                            tv_shares.text="${user.social.shares}"
                            tv_friends.text="${user.social.friends}"

                            tv_age.text="${user.age}"
                            tv_mail.text="${user.email}"
                            tv_location.text="${user.location}"
                            tv_work.text="${user.occupation}"

                            //Toast.makeText(this@MainActivity,"Usuario ${user.username} tiene amigos",Toast.LENGTH_LONG).show()
                        }
                    }else{
                        Toast.makeText(this@MainActivity,"Error ${response.code()}",Toast.LENGTH_LONG).show()
                    }
                }catch (e: HttpException){
                    Toast.makeText(this@MainActivity,"Error ${e.message()}",Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
