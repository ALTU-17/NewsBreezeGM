package com.altu.greedygame.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.altu.greedygame.NewsBreezeApp
import java.text.SimpleDateFormat
import java.util.*

object CommonUtils {
    val APIKEY = "30aba1b058aa42cbb92f030f0ed6d11d"
    fun currentDate() :String{
        val current = Calendar.getInstance()
        try {
            val outputFormat = SimpleDateFormat("yyyy-MM-dd")
            return outputFormat.format(current.time)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return ""
    }


    fun displayTime(time: String): String {
        try {
            val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH)
            val outputFormat = SimpleDateFormat("dd-MM-yyyy")
            val date: Date = inputFormat.parse(time)
            return outputFormat.format(date)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return ""
    }


    fun isConnected():Boolean{
        var isConnectedWithWifi=false
        var isConnectedWithMobile=false


        var connectionManager=NewsBreezeApp.instance.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if(connectionManager!=null)
        {
            if(Build.VERSION.SDK_INT<23)
            {
                val networkInfo=connectionManager.activeNetworkInfo
                if(networkInfo!=null)
                {
                    return (networkInfo.isConnected&&(networkInfo.type==ConnectivityManager.TYPE_WIFI)||(networkInfo.type==ConnectivityManager.TYPE_MOBILE))
                }
            }
            else{
                val networkInfo=connectionManager.activeNetwork
                if(networkInfo!=null)
                {
                    val networkCapabilities=connectionManager.getNetworkCapabilities(networkInfo)
                    return (networkCapabilities!!.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)||networkCapabilities!!.hasTransport(NetworkCapabilities.TRANSPORT_WIFI))
                }
            }
        }
        return isConnectedWithMobile||isConnectedWithWifi

    }

}