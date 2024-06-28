package com.example.translationapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform