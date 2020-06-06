package com.kriticalflare.siesgstarena.authentication

import android.content.SharedPreferences

class AuthenticationManager(private val sharedPref: SharedPreferences) {
    fun isAuthenticated(): Boolean =
        sharedPref.getString("username", "")!!.isNotEmpty()

    fun saveRegistration(username: String) {
        sharedPref.edit().putString("username", username).apply()
    }

    fun clearRegistration() {
        sharedPref.edit().remove("username").apply()
    }

    fun getAuthenticatedUser(): String {
        return checkNotNull(sharedPref.getString("username", "").takeIf { it!!.isNotEmpty() })
    }
}
