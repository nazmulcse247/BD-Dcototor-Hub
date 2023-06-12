package com.homairaahmed.bddoctorhub.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {

    @GET
    suspend fun getScapeData(@Url url: String) : Response<String>
}