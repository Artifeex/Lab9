package com.example.lab9

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import com.example.lab9.databinding.ActivityTask2Binding
import java.util.Timer

class ActivityTask2 : AppCompatActivity() {

    val images = mutableListOf<Int>()

    val binding by lazy {
        ActivityTask2Binding.inflate(layoutInflater)
    }

    private var imageIndex = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initImages()
        startAnimation()
    }

    private fun initImages() {
        images.add(R.drawable.walk_1)
        images.add(R.drawable.walk_2)
        images.add(R.drawable.walk_3)
        images.add(R.drawable.walk_4)
        images.add(R.drawable.walk_5)
        images.add(R.drawable.walk_6)
        images.add(R.drawable.walk_7)
        images.add(R.drawable.walk_8)
        images.add(R.drawable.walk_9)
        images.add(R.drawable.walk_10)
        images.add(R.drawable.walk_11)
        images.add(R.drawable.walk_12)
    }

    private fun startAnimation() {
        val timer = object : CountDownTimer(100000L, 75) {
            override fun onTick(millisUntilFinished: Long) {
                imageIndex++
                if(imageIndex >= images.size) {
                    imageIndex = 0
                }
                binding.ivAnimation.setImageResource(images[imageIndex])
            }

            override fun onFinish() {

            }
        }
        timer.start()
    }

    companion object  {
        fun newIntent(context: Context) = Intent(context, ActivityTask2::class.java)
    }
}