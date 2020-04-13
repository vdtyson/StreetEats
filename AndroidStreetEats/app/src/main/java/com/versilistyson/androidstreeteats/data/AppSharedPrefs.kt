package com.versilistyson.androidstreeteats.data

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject

class AppSharedPrefs
@Inject constructor(private val sharedPreferences: SharedPreferences) {
    companion object {
        private const val UID_KEY = "UID_KEY"
        private const val ACCOUNT_SETUP_COMPLETION_LEVEL_KEY = "ACCOUNT_SETUP_COMPLETION_LEVEL_KEY"
    }

    private val editor =
        sharedPreferences.edit()

    fun writeUserId(uid: String) {
        editor.putString(UID_KEY, uid).commit()
    }
    fun deleteUserId() {
        editor.remove(UID_KEY).commit()
    }
    fun fetchUserId(): String? =
        sharedPreferences.getString(UID_KEY, null)

    fun writeAccountSetupCompletionLevel(completionLevel: Int) {
        editor.putInt(ACCOUNT_SETUP_COMPLETION_LEVEL_KEY, completionLevel).commit()
    }

    fun deleteAccountSetupCompletionLevel() {
        editor.remove(ACCOUNT_SETUP_COMPLETION_LEVEL_KEY).commit()
    }

    fun fetchAccountSetupCompletionLevel(): Int =
        sharedPreferences.getInt(ACCOUNT_SETUP_COMPLETION_LEVEL_KEY, 0)
}