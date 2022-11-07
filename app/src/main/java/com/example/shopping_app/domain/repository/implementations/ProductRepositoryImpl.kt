package com.example.shopping_app.domain.repository.implementations

import com.example.shopping_app.data.model.JeweleryProducts
import com.example.shopping_app.data.model.Products
import com.example.shopping_app.data.remote.source.ShoppingRemoteDataSource
import com.example.shopping_app.data.remote.utils.DataState
import com.example.shopping_app.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class ProductRepositoryImpl @Inject constructor(private val shoppingRemoteDataSource: ShoppingRemoteDataSource)
    : ProductRepository {
    override suspend fun getProductDetail(productId: Int): Flow<DataState<Products>> {
         return shoppingRemoteDataSource.getShoppingAppDetail(productId)
        }
   /* override suspend fun getJeweleryDetail(page: Int): Flow<DataState<JeweleryProducts>> {
        return shoppingRemoteDataSource.getJeweleryDetail(page)
    }*/

    //jewelery ben ekledim

}