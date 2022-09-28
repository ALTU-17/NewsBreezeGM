package com.altu.greedygame.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.altu.greedygame.data.local.dao.NewsDao
import com.altu.greedygame.data.local.entity.News
import com.altu.greedygame.data.repository.Repository
import com.altu.greedygame.data.repository.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val newsRepository: Repository,private val newsDao: NewsDao) :ViewModel() {

    fun getNews() = liveData<Resource<List<News>>> {
        newsRepository.getNews().onStart {
            emit(Resource.loading(data=null))
        }.catch {
            emit(Resource.error(data = null, msg = "Some error occured"))
        }.collect {
            emit(Resource.success(it))
        }
    }

    fun getNewsById(id:Int) = liveData<Resource<News>> {
        newsRepository.getNewsById(id).onStart {
            emit(Resource.loading(null))
        }.catch {
            emit(Resource.error(data = null,msg="Some error occured"))
        }.collect {
            emit(Resource.success(it))
        }

    }


    fun update(news: News){
        viewModelScope.launch {
            newsRepository.update(news)
        }
    }


}