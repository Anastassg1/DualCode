package com.example.codebreak.storage

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "user_progress")

class ProgressManager(private val context: Context) {

    companion object {
        private val KEYS_COUNT = intPreferencesKey("keys_count")
        private val COMPLETED_MISSIONS = stringSetPreferencesKey("completed_missions")
    }

    val keysCountFlow: Flow<Int> = context.dataStore.data.map { preferences ->
        preferences[KEYS_COUNT] ?: 0
    }

    val completedMissionsFlow: Flow<Set<String>> = context.dataStore.data.map { preferences ->
        preferences[COMPLETED_MISSIONS] ?: emptySet()
    }

    suspend fun addKey() {
        context.dataStore.edit { preferences ->
            val current = preferences[KEYS_COUNT] ?: 0
            preferences[KEYS_COUNT] = current + 1
        }
    }

    suspend fun markMissionCompleted(missionId: Int) {
        context.dataStore.edit { preferences ->
            val currentMissions = preferences[COMPLETED_MISSIONS] ?: emptySet()
            preferences[COMPLETED_MISSIONS] = currentMissions + missionId.toString()
        }
    }
}