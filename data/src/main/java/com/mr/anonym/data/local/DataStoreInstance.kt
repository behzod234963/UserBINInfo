package com.mr.anonym.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore:DataStore<Preferences> by preferencesDataStore("BinInfo")

class DataStoreInstance( private val context: Context ) {

    suspend fun saveKey(key:Int){
        val intKey = intPreferencesKey("saveKey")
        context.dataStore.edit {
            it[intKey] = key
        }
    }
    fun getKey(): Flow<Int> {
        val intKey = intPreferencesKey("saveKey")
        return context.dataStore.data.map {
            it[intKey]?:-1
        }
    }
    suspend fun saveHistory(history:String){
        val stringKey = stringPreferencesKey("stringKey")
        context.dataStore.edit {
            it[stringKey] = history
        }
    }
    fun getHistory(): Flow<String> {
        val stringKey = stringPreferencesKey("stringKey")
        return context.dataStore.data.map {
            it[stringKey]?:""
        }
    }
}