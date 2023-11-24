package com.example.lab9

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class ActivityTask1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task1)
    }

    companion object {
        fun newIntent(context: Context) = Intent(context, ActivityTask1::class.java)
    }
}