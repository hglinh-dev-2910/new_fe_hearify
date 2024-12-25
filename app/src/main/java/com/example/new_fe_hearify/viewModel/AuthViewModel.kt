package com.example.new_fe_hearify.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.new_fe_hearify.data.LoginRequest
import com.example.new_fe_hearify.network.AuthRepository
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {
    private val repository = AuthRepository()

    //live data
    private val _registrationResult = MutableLiveData<String?>()
    val registrationResult: LiveData<String?> = _registrationResult

    private val _loginResult = MutableLiveData<Pair<String?, String?>?>()
    val loginResult: MutableLiveData<Pair<String?, String?>?> = _loginResult


    //regis method
    fun registerTraditional(username: String, password: String) {
        viewModelScope.launch {
            val request = com.example.new_fe_hearify.data.RegisterRequest("traditional", username = username, password = password)
            _registrationResult.value = repository.registerUser(request)
        }
    }

    fun registerOAuth(email: String, oauthID: String) {
        viewModelScope.launch {
            val request = com.example.new_fe_hearify.data.RegisterRequest("oauth", email = email, oauthID = oauthID)
            _registrationResult.value = repository.registerUser(request)
        }
    }


    //login method
    fun loginTraditional(username: String, password: String) {
        viewModelScope.launch {
            val request = LoginRequest(
                method = "traditional",
                username = username,
                password = password
            )
            _loginResult.value = repository.loginUser(request)
        }
    }

    fun loginOAuth(email: String, oauthID: String) {
        viewModelScope.launch {
            val request = LoginRequest(
                method = "oauth",
                email = email,
                oauthID = oauthID
            )
            _loginResult.value = repository.loginUser(request)
        }
    }
    override fun onCleared() {
        super.onCleared()
        repository.closeClient()
    }

    fun resetLoginResult() {
        _loginResult.value = null

    }

    fun resetRegistrationResult() {
        _registrationResult.value = null
    }


}