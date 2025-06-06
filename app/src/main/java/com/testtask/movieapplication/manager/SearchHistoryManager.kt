package com.testtask.movieapplication.manager

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// Имя файла DataStore
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "search_history")

class SearchHistoryManager(private val context: Context) {

    private val searchHistoryKey = stringSetPreferencesKey("search_history")

    // Получаем историю поиска как Flow<Set<String>>
    val searchHistoryFlow: Flow<Set<String>> = context.dataStore.data
        .map { preferences ->
            preferences[searchHistoryKey] ?: emptySet()
        }

    // Сохраняем запрос в историю
    suspend fun saveSearchQuery(query: String) {
        context.dataStore.edit { preferences ->
            val currentHistory = preferences[searchHistoryKey] ?: emptySet()
            val mutableHistory = currentHistory.toMutableSet()
            mutableHistory.add(query)

            // Ограничиваем размер истории
            while (mutableHistory.size > 10) {
                mutableHistory.remove(mutableHistory.elementAt(0))
            }

            preferences[searchHistoryKey] = mutableHistory
        }
    }

    // Удаляем запрос из истории
    suspend fun deleteSearchQuery(query: String) {
        context.dataStore.edit { preferences ->
            val currentHistory = preferences[searchHistoryKey] ?: emptySet()
            val mutableHistory = currentHistory.toMutableSet()
            mutableHistory.remove(query)
            preferences[searchHistoryKey] = mutableHistory
        }
    }

    // Очищаем всю историю
    suspend fun clearSearchHistory() {
        context.dataStore.edit { preferences ->
            preferences.remove(searchHistoryKey)
        }
    }
}