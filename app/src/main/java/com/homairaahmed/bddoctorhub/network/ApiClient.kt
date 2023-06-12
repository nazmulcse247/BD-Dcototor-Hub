package com.homairaahmed.bddoctorhub.network

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

class ApiClient {

    companion object {

        fun getRetrofit(): Retrofit {
            return Retrofit.Builder()
                .addConverterFactory(ScalarsConverterFactory.create())
                .baseUrl("https://www.doctorbangladesh.com/")
                .build()
        }
    }
}