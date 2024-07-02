package com.zgy.kotlinapplication.extension

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.SharedPreferencesMigration
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.zgy.kotlinapplication.App

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = App.context.packageName + "_preferences",
    produceMigrations = {context ->
        listOf(SharedPreferencesMigration(context, App.context.packageName + "_preferences"))
    }
)