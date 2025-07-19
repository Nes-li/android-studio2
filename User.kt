package com.example.kuaforhatirlatici.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey val email: String,
    val displayName: String?,
    val registerDate: Long = System.currentTimeMillis(),
    val googleId: String? = null // Sadece Google ile giriş yapanlar için
)
