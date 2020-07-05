package com.bancario.montcomp.appkotlin_v1.Network

import com.bancario.montcomp.examen_everis.Network.PostReponse
import retrofit2.http.GET

interface Service {
    @GET("profile")
    suspend fun getprofile():retrofit2.Response<UserResponse>

    @GET("posts")
    suspend fun getPosts():retrofit2.Response<List<PostReponse>>

    @GET("users")
    suspend fun getFriends():retrofit2.Response<List<UserResponse>>

    /*@POST("post/save")
    suspend fun savePost(@Body image: String,@Body username: String,@Body comment: String ): retrofit2.Response<List<PostReponse>>
*/

}