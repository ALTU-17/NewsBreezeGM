package com.altu.greedygame.ui.saved_news.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.altu.greedygame.R
import com.altu.greedygame.data.local.entity.News
import com.altu.greedygame.databinding.ItemSavedNewsBinding
import com.altu.greedygame.utils.CommonUtils


class SaveNewsAdapter (val context: Context) : RecyclerView.Adapter<SaveNewsAdapter.ViewHolder>() {

    private var list = emptyList<News>()
    var onItemClick: ((newsId: Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SaveNewsAdapter.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_saved_news, parent, false)
        return SaveNewsAdapter.ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: SaveNewsAdapter.ViewHolder, position: Int) {
        val model = list[position]
        with(holder) {
            binding.textName.text = "#${model.Name}"
            binding.textTitle.text = model.Title
            binding.textDate.text = model.Date?.let { CommonUtils.displayTime(it) }
            Glide.with(context).load(model.Image).into(binding.imgSaveNews)

            binding.parentContainer.setOnClickListener {
                onItemClick!!.invoke(model.NewsID)
            }

        }
    }


    internal fun setNews(words: List<News>) {
        this.list = words
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ItemSavedNewsBinding.bind(itemView)
    }
}

