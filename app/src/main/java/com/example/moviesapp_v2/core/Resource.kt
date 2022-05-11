package com.example.moviesapp_v2.core

import java.lang.Exception

//States management
sealed class Resource<out T> {
    class Loading<out T>: Resource<T>()
    data class Success<out T>(val data: T): Resource<T>()
    data class Failure(val exception: Exception): Resource<Nothing>()
}