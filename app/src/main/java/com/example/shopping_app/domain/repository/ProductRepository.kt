package com.example.shopping_app.domain.repository

import com.example.shopping_app.data.model.JeweleryProducts
import com.example.shopping_app.data.model.Products
import com.example.shopping_app.data.remote.utils.DataState
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    suspend fun getProductDetail(productId : Int): Flow<DataState<Products>>
    //suspend fun getJeweleryDetail(page:Int): Flow<DataState<JeweleryProducts>>
}