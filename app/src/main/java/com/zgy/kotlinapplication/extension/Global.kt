package com.zgy.kotlinapplication.extension

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.zgy.kotlinapplication.App

val dataStore: DataStore<Preferences> = App.context.dataStore