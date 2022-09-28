package com.altu.greedygame.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "news_table")
data class News(
    var Title: String?,
    var Description: String?,
    var Author:String?,
    var Image:String?,
    var Url:String?,
    var Date:String?,
    var Content:String?,
    var Name:String?,
    var Saved:Int?,
    @PrimaryKey(autoGenerate = true) val NewsID: Int = 0)