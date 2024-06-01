package com.uretouch.domain.auth.model

data class User(
    val email: String,
    val id: String,
    val isActivated: Boolean,
)