package com.example.moviesapp_v2.presentation.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.example.moviesapp_v2.core.Resource
import com.example.moviesapp_v2.repository.auth.LoginRepo
import kotlinx.coroutines.Dispatchers

class LoginScreenViewModel(private val repo: LoginRepo): ViewModel() {

    fun signIn(email: String, password: String) = liveData(Dispatchers.IO) {
        //Emit info to the UI
        emit(Resource.Loading())

        try {
            emit(
                Resource.Success(repo.signIn(email, password)))
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }
}


class LoginScreenViewModelFactory(private val repo: LoginRepo): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LoginScreenViewModel(repo) as T
    }
}

