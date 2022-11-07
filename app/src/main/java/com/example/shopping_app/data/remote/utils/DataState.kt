package com.example.shopping_app.data.remote.utils

import com.example.shopping_app.data.model.ApiError

sealed class DataState<T>{
    data class Success<T>(val data: T): DataState<T>()
    data class Error<T>(val api: ApiError?): DataState<T>()
    class Loading<T> : DataState<T>()

}
