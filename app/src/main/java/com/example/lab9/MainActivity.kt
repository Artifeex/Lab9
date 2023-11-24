package com.example.lab9

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lab9.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initButtonListeners()
    }

    private fun initButtonListeners() {
        binding.bnTask1.setOnClickListener {
            startActivity(ActivityTask1.newIntent(this))
        }
        binding.bnTask2.setOnClickListener {
            startActivity(ActivityTask2.newIntent(this))
        }

        binding.bnTask3.setOnClickListener {
            startActivity(ActivityTask3.newIntent(this))
        }

        binding.bnTask4.setOnClickListener {
            startActivity(ActivityTask4.newIntent(this))
        }
        binding.bnTask5.setOnClickListener {
            startActivity(ActivityTask5.newIntent(this))
        }
        binding.bnTask6.setOnClickListener {
            startActivity(ActivityTask6.newIntent(this))
        }
    }
}