package com.online.coffee.shop.app.view

import android.os.Bundle
import com.online.coffee.shop.app.R
import com.online.coffee.shop.app.base.BaseActivity

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}