package com.example.marvelapp.util.API

import com.example.marvelapp.model.Characters
import retrofit2.Call
import retrofit2.http.GET

interface APIService {

    @GET("marvel/")
    fun getAllCharacters(): Call<List<Characters>>


}