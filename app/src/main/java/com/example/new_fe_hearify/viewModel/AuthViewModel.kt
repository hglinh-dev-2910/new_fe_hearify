package com.example.new_fe_hearify.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.new_fe_hearify.data.LoginRequest
import com.example.new_fe_hearify.data.RegisterRequest
import com.example.new_fe_hearify.network.AuthRepository
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import kotlinx.coroutines.launch


class AuthViewModel : ViewModel() {
    private val repository = AuthRepository()
    val loading = MutableLiveData<Boolean>()
    val error = MutableLiveData<String?>()
    val registrationResult = MutableLiveData<String?>()


    fun registerOAuth(email: String, oauthID: String) {
        viewModelScope.launch {
            loading.postValue(true)
            try {
                val request = RegisterRequest(
                    method = "oauth",
                    email = email,
                    oauthID = oauthID
                )
                val response = repository.registerUser(request)
                registrationResult.postValue(response)
            } catch (e: Exception) {
                error.postValue(e.localizedMessage)
            } finally {
                loading.postValue(false)
            }
        }
    }
}
