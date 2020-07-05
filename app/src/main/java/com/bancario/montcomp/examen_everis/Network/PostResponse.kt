package com.bancario.montcomp.examen_everis.Network


data class PostReponse(
    val id: Int,
    val user_id: Int,
    val username: String,
    val user_image: String,
    val body: String,
    val image: String,
    val likes: Int,
    val comment: List<Comment>
)