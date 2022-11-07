package com.example.shopping_app.data.remote.source.implementations

import android.provider.ContactsContract
import com.example.shopping_app.data.model.ApiError
import com.example.shopping_app.data.remote.utils.DataState
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import okhttp3.Dispatcher
import retrofit2.Response
import kotlinx.coroutines.flow.*

//imp edince içindeki fonk ulaşmak için open diyoruz
open class BaseRemoteDataSource {
    suspend fun <T> getResult(call: suspend () -> Response<T>) : Flow<DataState<T>> {
        return flow<DataState<T>> {
            val response= call()

            if (response.isSuccessful){
                val body = response.body()

                if (body != null) emit(DataState.Success(body))
                else{
                    val apiError :ApiError = Gson().fromJson(response.errorBody()?.charStream(), ApiError::class.java)
                    emit(DataState.Error(apiError))
                }
            }else{
                val apiError: ApiError =
                    Gson().fromJson(response.errorBody()?.charStream(), ApiError::class.java)
                emit(DataState.Error(apiError))
            }
        }.catch { e ->
            emit(DataState.Error(ApiError(-1, e.message ?: "Unkown Error")))
        }.onStart { emit(DataState.Loading()) }
            .flowOn(Dispatchers.IO)
    }
}