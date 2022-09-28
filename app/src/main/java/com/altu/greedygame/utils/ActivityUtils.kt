package com.altu.greedygame.utils

import android.app.Activity
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

fun Activity.showToast(message:String,duration:Int=Toast.LENGTH_LONG)
{
    Toast.makeText(applicationContext,message,duration).show()
}

/**
 * Provides ViewModel of Type VM from factory
 *
 */
inline fun <reified VM : ViewModel> AppCompatActivity.viewModelOf(
    factory: ViewModelProvider.Factory
) =ViewModelProvider(this,factory).get(VM::class.java)