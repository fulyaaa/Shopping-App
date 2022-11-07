package com.example.shopping_app.feature.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopping_app.MainActivity
import com.example.shopping_app.feature.login.LoginUiState
import com.example.shopping_app.feature.login.LoginViewEvent
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val dataStoreManager: MainActivity,
                                            private val firebaseAuth: FirebaseAuth, ) : ViewModel() {
    private val _uiState = MutableStateFlow<LoginUiState>(LoginUiState.Empty)
    val uiState : StateFlow<LoginUiState> = _uiState

    private val _uiEvent = MutableSharedFlow<RegisterViewEvent>(replay = 0)
    val uiEvent: SharedFlow<RegisterViewEvent> = _uiEvent

    fun register(email: String, password: String, username: String){
        viewModelScope.launch {
            if (isValidFields(email, password, username)){
                firebaseAuth.signInWithEmailAndPassword(
                    email,
                    password
                ).addOnCompleteListener{ task ->
                    if (task.isSuccessful){
                        viewModelScope.launch {
                            _uiEvent.emit(RegisterViewEvent.NavigateToMain)
                        }
                    }else{
                        viewModelScope.launch {
                            _uiEvent.emit(RegisterViewEvent.ShowError(task.exception?.message.toString()))
                        }
                    }
                }

            }else{
                _uiEvent.emit(RegisterViewEvent.ShowError("Please fill all fields"))
            }

        }
    }

    private fun isValidFields(email: String,password: String, username: String): Boolean{
        return email.isNotEmpty() && password.isNotEmpty() && username.isNotEmpty()
    }

}

sealed class RegisterViewEvent{
    object NavigateToMain: RegisterViewEvent()
    class ShowError(val error: String) : RegisterViewEvent()
}