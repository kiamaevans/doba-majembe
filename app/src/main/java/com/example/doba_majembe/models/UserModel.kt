package com.example.doba_majembe.models

data class UserModel(
    val userId: String = "",           // Firebase UID
    val username: String = "",      // From Spotify or user input
    val email: String = "",            // Firebase Auth email
    val spotifyId: String? = null,     // Spotify unique ID
    val role: String = "User",         // "User" or "Admin"
    val profileImageUrl: String? = null, // optional profile picture
    val createdAt: Long = System.currentTimeMillis() // timestamp



)
