package com.example.shopping_app.data.remote.source.implementations

import com.example.shopping_app.data.model.JeweleryProducts
import com.example.shopping_app.data.model.Products
import com.example.shopping_app.data.remote.api.ProductsService
import com.example.shopping_app.data.remote.source.ShoppingRemoteDataSource
import com.example.shopping_app.data.remote.utils.DataState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

//api ile haberleşip, remotedaki datayı handle atıcak
class ShoppingRemoteDataSourceImpl @Inject constructor(private val productsService: ProductsService) :
    BaseRemoteDataSource(), ShoppingRemoteDataSource {
    override suspend fun getShoppingAppDetail(shoppingAppId: Int): Flow<DataState<Products>> {
        return getResult { productsService.getShoppingAppDetail(shoppingAppId) }
    }

   /* override suspend fun getJeweleryDetail(page: Int): Flow<DataState<JeweleryProducts>> {
        return getResult { productsService.getShoppingAppDetail(page) }
    }*/

}
