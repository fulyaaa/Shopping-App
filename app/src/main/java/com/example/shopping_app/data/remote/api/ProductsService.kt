package com.example.shopping_app.data.remote.api

import com.example.shopping_app.data.model.Products
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductsService {

    //@GET("products")
    //suspend fun getPopularMovies(): Response<Products>

    @GET("products")
    suspend fun getShoppingAppDetail(@Query("id") id:Int) : Response<Products>

}