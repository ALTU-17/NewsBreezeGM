package com.altu.greedygame.ui.base.listeners

import com.altu.greedygame.data.local.entity.News

interface RecyclerItemListener {
    fun onItemSelected(news:News)
}