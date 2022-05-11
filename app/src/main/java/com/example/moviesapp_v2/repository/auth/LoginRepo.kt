package com.example.moviesapp_v2.repository.auth

import com.google.firebase.auth.FirebaseUser

interface LoginRepo {
    suspend fun signIn(email:String,password:String): FirebaseUser?
}