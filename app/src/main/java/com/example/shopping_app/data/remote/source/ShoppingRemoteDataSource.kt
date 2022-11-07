package com.example.shopping_app.data.remote.source

import com.example.shopping_app.data.model.JeweleryProducts
import com.example.shopping_app.data.model.Products
import com.example.shopping_app.data.remote.api.ProductsService
import com.example.shopping_app.data.remote.utils.DataState
import kotlinx.coroutines.flow.Flow

//responselarla birlikte retrofitle haberleşecek-flowla birliktedata katmanı yollayacak
interface ShoppingRemoteDataSource {
    suspend fun getShoppingAppDetail(shoppingAppId: Int) : Flow<DataState<Products>>
    //suspend fun getJeweleryDetail(page: Int): Flow<DataState<JeweleryProducts>>
    //Product mı olacak? kontrol
}