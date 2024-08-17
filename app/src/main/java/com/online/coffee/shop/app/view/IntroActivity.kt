package com.online.coffee.shop.app.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.widget.AppCompatButton
import com.online.coffee.shop.app.R
import com.online.coffee.shop.app.base.BaseActivity

class IntroActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContentView(R.layout.activity_intro)
        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }*/

        val btnStarted = findViewById<AppCompatButton>(R.id.btn_started)
        btnStarted.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}