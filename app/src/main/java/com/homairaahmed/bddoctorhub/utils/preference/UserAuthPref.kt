package com.homairaahmed.bddoctorhub.utils.preference

import android.content.Context
import com.homairaahmed.bddoctorhub.utils.Constrant.Companion.USER_AUTH_PREF
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class UserAuthPref @Inject constructor(@ApplicationContext context: Context) {

    private val pref = context.getSharedPreferences(USER_AUTH_PREF, Context.MODE_PRIVATE)

    fun saveUserAuthData() {
        pref.edit().putBoolean("isUserLoggedIn", true).apply()
    }

    fun getUserAuthData(): Boolean {
        return pref.getBoolean("isUserLoggedIn", false)
    }

}
