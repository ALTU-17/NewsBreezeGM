package com.altu.greedygame.data.repository

import com.altu.greedygame.data.local.entity.News
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun getNews(): Flow<List<News>>
    suspend fun getNewsById(id: Int): Flow<News>
    suspend fun getSavedNews(): Flow<List<News>>
    suspend fun update(news: News)
}