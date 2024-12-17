package com.example.new_fe_hearify.auth

import android.app.Activity
import android.content.Context
import android.util.Log
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import androidx.credentials.GetCredentialResponse

import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.common.api.ApiException
import kotlinx.coroutines.tasks.await

class GoogleSignInHelper(private val context: Context) {

    private val oneTapClient: SignInClient
    private val credentialManager: CredentialManager

    init {
        oneTapClient = Identity.getSignInClient(context)
        credentialManager = CredentialManager.getInstance(context)
    }

    suspend fun signIn(activity: Activity): String? {
        val request = GetCredentialRequest.Builder()
            .setPasswordLoginSupported(true)
            .build()

        return try {
            val response: GetCredentialResponse = credentialManager.getCredential(context, request).await()
            response.credential?.id
        } catch (e: Exception) {
            Log.e("GoogleSignInHelper", "Failed to get credential", e)
            null
        } ?: run {
            // No credential found, initiate Google Sign-In
            val signInRequest = BeginSignInRequest.builder()
                .setPasswordRequestOptions(
                    BeginSignInRequest.PasswordRequestOptions.builder()
                        .setSupported(true)
                        .build()
                )
                .setGoogleIdTokenRequestOptions(
                    BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                        .setSupported(true)
                        .setServerClientId(AuthHelper.getServerClientId(context))
                        .setFilterByAuthorizedAccounts(false)
                        .build()
                )
                .setAutoSelectEnabled(false)
                .build()

            try {
                val result = oneTapClient.beginSignIn(signInRequest).await()
                // Launch the intent for result (You'll need to handle this in your Activity)
                activity.startActivityForResult(result.pendingIntent.intentSender, 100)
                null // or handle the result here if possible
            } catch (e: ApiException) {
                Log.w("GoogleSignInHelper", "Google Sign In failed", e)
                null
            }
        }
    }
}