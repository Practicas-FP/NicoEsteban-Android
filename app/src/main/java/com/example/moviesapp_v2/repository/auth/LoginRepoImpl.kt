package com.example.moviesapp_v2.repository.auth

import com.example.moviesapp_v2.data.remote.auth.LoginDataSource
import com.google.firebase.auth.FirebaseUser

class LoginRepoImpl(private val dataSource: LoginDataSource): LoginRepo {
    override suspend fun signIn(email: String, password: String): FirebaseUser? {
        return dataSource.signIn(email, password)
    }
}

/*
*
*
import android.graphics.Bitmap
import com.example.blogapp.data.remote.auth.AuthDataSource
import com.google.firebase.auth.FirebaseUser

class AuthRepoImpl(private val dataSource: AuthDataSource) : AuthRepo {
    override suspend fun signIn(email: String, password: String): FirebaseUser? =
        dataSource.signIn(email, password)

    override suspend fun signUp(email: String, password: String, username: String): FirebaseUser? = dataSource.signUp(email,password,username)
    override suspend fun updateProfile(imageBitmap: Bitmap, username: String) = dataSource.updateUserProfile(imageBitmap,username)
}
* */