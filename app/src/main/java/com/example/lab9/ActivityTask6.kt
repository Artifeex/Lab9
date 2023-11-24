package com.example.lab9

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.MediaController
import com.example.lab9.databinding.ActivityTask6Binding

class ActivityTask6 : AppCompatActivity() {

    private val binding by lazy {
        ActivityTask6Binding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val videoPath = "android.resource://${packageName}/${R.raw.cats}"

        val mediaController = MediaController(this)
        mediaController.setAnchorView(binding.videoView)

        binding.videoView.setMediaController(mediaController)

        binding.videoView.setVideoPath(videoPath)

        binding.videoView.start()
    }

    companion object {
        fun newIntent(context: Context) = Intent(context, ActivityTask6::class.java)
    }
}