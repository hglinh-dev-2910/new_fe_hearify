package com.example.new_fe_hearify.network

import com.example.new_fe_hearify.data.LoginRequest
import com.example.new_fe_hearify.data.RegisterRequest
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*

import io.ktor.client.request.*
import io.ktor.client.statement.bodyAsText
import io.ktor.http.*
import kotlinx.serialization.json.Json
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

class AuthRepository {

    private val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                isLenient = true
                encodeDefaults = true
            })
        }
    }

    suspend fun registerUser(request: RegisterRequest): String? {
        return try {

            val jsonRequest = Json.encodeToString(request) // Convert to JSON string
            println("Sending JSON: $jsonRequest") // Log the JSON

            client.post("http://10.0.2.2:8080/register") {
                contentType(ContentType.Application.Json)
                setBody(request)
            }.bodyAsText()

        } catch (e: Exception) {
            println("Registration error: ${e.message}")
            null
        }
    }
    suspend fun loginUser(request: LoginRequest): Pair<String?, String?> {
        return try {
            val response = client.post("http://10.0.2.2:8080/login") {
                contentType(ContentType.Application.Json)
                setBody(request)
            }

            val responseBody = response.bodyAsText()
            println("Login Response: $responseBody")

            val responseJson = Json.parseToJsonElement(responseBody).jsonObject
            val token = responseJson["token"]?.jsonPrimitive?.content
            val message = responseJson["message"]?.jsonPrimitive?.content

            Pair(token, message)
        } catch (e: Exception) {
            e.printStackTrace()
            println("Login error: ${e.message}")
            Pair(null, "Login failed: ${e.message}")
        }
    }

    fun closeClient(){
        client.close()
    }
}