package com.example.new_fe_hearify.auth

import android.content.Context
import java.util.Properties

object AuthHelper {
    fun getServerClientId(context: Context): String {
        val assetManager = context.assets
        val inputStream = assetManager.open("auth.conf")
        val properties = Properties()
        properties.load(inputStream)
        return properties.getProperty("serverClientId")
    }
}