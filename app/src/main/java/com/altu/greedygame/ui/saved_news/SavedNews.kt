package com.altu.greedygame.ui.saved_news

import android.os.Bundle
import androidx.activity.viewModels
import com.altu.greedygame.data.local.entity.News
import com.altu.greedygame.databinding.ActivitySavedNewsBinding
import com.altu.greedygame.ui.base.BaseActivity
import com.altu.greedygame.ui.home.HomeViewModel
import com.altu.greedygame.ui.saved_news.adapter.SaveNewsAdapter

class SavedNews : BaseActivity<HomeViewModel,ActivitySavedNewsBinding>() {

    override val viewModel: HomeViewModel by viewModels()
    var saveNewsAdapter: SaveNewsAdapter? = null

    var newsList:ArrayList<News> = ArrayList()
    var tempList:ArrayList<News> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mViewBinding.root)

    }

    override fun getViewBinding(): ActivitySavedNewsBinding = ActivitySavedNewsBinding.inflate(layoutInflater)
}