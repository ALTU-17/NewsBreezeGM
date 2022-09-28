
package com.altu.greedygame.ui.home

import android.app.Activity
import android.os.Bundle
import com.altu.greedygame.R
import android.content.Intent
import android.os.Handler

class SplashActivity : Activity() {
    var handler: Handler? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        handler = Handler()
        handler!!.postDelayed({
            val intent = Intent(this@SplashActivity, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }
}