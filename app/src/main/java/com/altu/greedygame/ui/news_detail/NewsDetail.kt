package com.altu.greedygame.ui.news_detail

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.altu.greedygame.R
import com.altu.greedygame.data.local.entity.News
import com.altu.greedygame.data.repository.Status
import com.altu.greedygame.databinding.ActivityNewsDetailBinding
import com.altu.greedygame.ui.base.BaseActivity
import com.altu.greedygame.ui.home.HomeViewModel

class NewsDetail : BaseActivity<HomeViewModel,ActivityNewsDetailBinding>() {
    override val viewModel: HomeViewModel by viewModels()
    var newsId = 0
    var news: News? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mViewBinding.root)
        val intent = intent
        if (intent!=null){
            newsId = intent.getIntExtra("newsId",0)
        }

        getNewsFromLocal(newsId)
        setOnclickListener()

    }

    private fun setOnclickListener() {
        mViewBinding.imgBackDetail.setOnClickListener {
            onBackPressed()
        }
        mViewBinding.btnSaved.setOnClickListener {
            saveFunction()
        }
        mViewBinding.imgBookMark.setOnClickListener {
            saveFunction()
        }
    }

    private fun saveFunction() {
        val status = if(news?.Saved == 0){
            1
        } else {
            0
        }

        val news = news
        news?.Saved = status
        viewModel.update(news!!)
    }
    private fun getNewsFromLocal(newsId: Int) {
        viewModel.getNewsById(newsId).observe(this, Observer {
            it?.let {
                when(it.status){
                    Status.LOADING -> {
                    }
                    Status.SUCCESS -> {
                        if (it.data!=null){
                            news = it.data
                            mViewBinding.collapsingToolbar.title = it.data.Title
                            mViewBinding.textContent.text = it.data.Content
                            mViewBinding.textAuthor.text = it.data.Author
                            if (it.data.Saved == 0){
                                mViewBinding.btnSaved.text = getString(R.string.save)
                                mViewBinding.imgBookMark.setImageResource(R.drawable.ic_save_outline)


                            }else {
                                mViewBinding.btnSaved.text = getString(R.string.saved)
                                mViewBinding.imgBookMark.setImageResource(R.drawable.ic_save)
                            }
                            Glide.with(this).load(it.data.Image).into(mViewBinding.imgBanner)

                        }
                    }
                    Status.ERROR -> {
                        Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }

    override fun getViewBinding(): ActivityNewsDetailBinding = ActivityNewsDetailBinding.inflate(layoutInflater)
}