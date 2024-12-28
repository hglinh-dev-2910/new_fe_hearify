package com.example.new_fe_hearify.data

import kotlinx.serialization.Serializable
import java.time.Instant

@Serializable
data class RegisterRequest(
    val method: String,
    val username: String? = null,
    val password: String? = null,
    val email: String? = null,
    val oauthID: String? = null
)

@Serializable
data class LoginRequest(
    val method: String,
    val username: String? = null,
    val password: String? = null,
    val email: String? = null,
    val oauthID: String? = null
)

@Serializable
data class ChatMessage(
    val id: Int = 0,
    val senderId: Int,
    val receiverId: Int,
    val content: String,
    val timestamp: Long = Instant.now().toEpochMilli(),
    val isRead: Boolean = false,
    val status: String = "pending" //default
)

@Serializable
data class ReadMessage(
    val action: String,
    val messageIds: List<Int>
)

@Serializable
data class Conversation(
    val receiverId: Int,
    val lastMessage: String,
    val timestamp: Long
)

@Serializable
data class User(
    val id: Int,
    val username: String

)