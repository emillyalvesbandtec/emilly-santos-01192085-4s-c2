package com.example.continuada_c3

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiCachorros {


    @GET()
    fun get(): Call<List<Cachorro>>

    @POST()
    fun post(@Body novoCachorro:Cachorro): Call<Cachorro>
}