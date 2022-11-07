package com.example.shopping_app.data.di

import com.example.shopping_app.data.remote.api.ProductsService
import com.example.shopping_app.data.remote.source.ShoppingRemoteDataSource
import com.example.shopping_app.data.remote.source.implementations.BaseRemoteDataSource
import com.example.shopping_app.data.remote.source.implementations.ShoppingRemoteDataSourceImpl
import com.example.shopping_app.domain.repository.ProductRepository
import com.example.shopping_app.domain.repository.implementations.ProductRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ShoppingsModule {

    @Singleton
    @Provides
    fun provideProductService(retrofit: Retrofit) = retrofit.create(ProductsService::class.java)

    @Singleton
    @Provides
    fun provideProductRemoteDataSource(productsService: ProductsService): ShoppingRemoteDataSource =
        ShoppingRemoteDataSourceImpl(productsService)

    @Singleton
    @Provides
    fun provideProductRepository(productRemoteDataSource: ShoppingRemoteDataSource): ProductRepository =
        ProductRepositoryImpl(productRemoteDataSource)
}