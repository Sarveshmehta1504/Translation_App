package com.example.translationapp.core.domain.util

sealed class Resources<T>(val data: T?,val throwable: Throwable?= null ) {
    class Success<T>(data: T): Resources<T>(data)
    class Error<T>(throwable: Throwable): Resources<T>(null, throwable)

}