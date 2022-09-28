package com.altu.greedygame.ui.home

import HomeAdapter
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.altu.greedygame.data.local.entity.News
import com.altu.greedygame.data.repository.Status
import com.altu.greedygame.databinding.ActivityHomeBinding
import com.altu.greedygame.ui.base.BaseActivity
import com.altu.greedygame.ui.news_detail.NewsDetail
import com.altu.greedygame.ui.saved_news.SavedNews
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : BaseActivity<HomeViewModel,ActivityHomeBinding>() {

    override val viewModel: HomeViewModel by viewModels()
    var sort = 1
    var newsList:ArrayList<News> = ArrayList()
    var tempList:ArrayList<News> = ArrayList()
    var homeAdapter:HomeAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mViewBinding.root)
        initAdapters()
        setOnClickListener()
        getNews()

    }

    private fun initAdapters() {
            mViewBinding.recyclerViewNews.layoutManager = LinearLayoutManager(this)
            homeAdapter = HomeAdapter(this)
            mViewBinding.recyclerViewNews.adapter = homeAdapter
        }


    override fun getViewBinding(): ActivityHomeBinding = ActivityHomeBinding.inflate(layoutInflater)




    private fun getNews() {
        viewModel.getNews().observe(this, Observer {
            it?.let {
                when(it.status){
                    Status.LOADING -> {
                        showProgress()
                    }
                    Status.SUCCESS -> {
                        dismissProgress()
                        if (it.data!!.isNotEmpty()){
                            mViewBinding.noDataAvailableText.visibility = View.GONE
                            newsList.clear()
                            newsList.addAll(it.data)
                        }
                        else {
                            mViewBinding.noDataAvailableText.visibility = View.VISIBLE
                        }
                    }
                    Status.ERROR -> {
                        dismissProgress()
                        mViewBinding.noDataAvailableText.visibility = View.VISIBLE
                        Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }


    private fun setOnClickListener() {
        homeAdapter!!.onItemClick = {isFrom,model ->

            // checking save status
            if (isFrom == "Save"){
                val status = if(model.Saved == 0){
                    1
                } else {
                    0
                }

                model.Saved = status
                viewModel.update(model)
            }
            else {
                val intent = Intent(this,NewsDetail::class.java)
                intent.putExtra("newsId",model.NewsID)
                startActivity(intent)
            }

        }
        mViewBinding.saveImageButton.setOnClickListener {
            startActivity(Intent(this,SavedNews::class.java))

        }
        mViewBinding.edtSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val txt = p0.toString()
                filterList(txt)
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })


    }


    private fun filterList(txt: String) {
        tempList.clear()
        if (!TextUtils.isEmpty(txt)) {
            val searchText = txt.toLowerCase()
            for (items in newsList) {
                val video = items.Title?.toLowerCase()
                if (video != null) {
                    if (video.contains(searchText)) {
                        tempList.add(items)
                    }
                }
            }
            homeAdapter?.setNews(tempList)
        } else {
            tempList.clear()
            homeAdapter?.setNews(newsList)
        }
    }


    fun showProgress(){
        mViewBinding.loader.visibility=View.VISIBLE
    }
    fun dismissProgress(){
        mViewBinding.loader.visibility=View.VISIBLE
    }


}