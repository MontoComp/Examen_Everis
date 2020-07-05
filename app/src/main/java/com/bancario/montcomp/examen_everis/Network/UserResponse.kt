package com.bancario.montcomp.appkotlin_v1.Network

import com.bancario.montcomp.examen_everis.Network.Social

data class UserResponse(
    val id: String,
    val username: String,
    val name: String,
    val lastname: String,
    val image: String,
    val occupation: String,
    val age: String,
    val email: String,
    val location:String,
    val phone:String,
    val social: Social
)