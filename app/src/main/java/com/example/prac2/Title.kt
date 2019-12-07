package com.example.prac2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class Title : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_title)
        Handler().postDelayed({ startActivity(Intent(baseContext, Main::class.java)) }, 3000L)
    }
}
