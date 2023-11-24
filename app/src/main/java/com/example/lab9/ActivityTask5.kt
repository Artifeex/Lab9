package com.example.lab9

import android.app.Notification
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lab9.databinding.ActivityTask5Binding

class ActivityTask5 : AppCompatActivity() {

    private lateinit var mediaPlayer: MediaPlayer

    private val binding by lazy {
        ActivityTask5Binding.inflate(layoutInflater)
    }

    private var currentSongIndex = 0

    private val songs = mutableListOf<Song>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        createSongs()
        binding.tvSongAuthor.text = songs[currentSongIndex].author
        binding.tvSongName.text = songs[currentSongIndex].name
        mediaPlayer = MediaPlayer.create(this, songs[currentSongIndex].uri)
        setButtonListeners()
    }

    private fun createSongs() {
        songs.add(Song("6Shots", "Neffex", R.raw.neffex_6shots))
        songs.add(Song("Bite Me", "Neffex", R.raw.neffex_bite_me))
        songs.add(Song("Blow Up", "Neffex", R.raw.neffex_blow_up))
    }

    private fun setButtonListeners() {
        binding.fabPlay.setOnClickListener {
            if(mediaPlayer.isPlaying) {
                mediaPlayer.pause()
                binding.fabPlay.setImageResource(R.drawable.baseline_play_arrow_24)
            } else {
                mediaPlayer.start()
                binding.fabPlay.setImageResource(R.drawable.baseline_pause_24)
            }
        }

        binding.prevSongButton.setOnClickListener {
            if(currentSongIndex == 0) {
                currentSongIndex = songs.size - 1
            } else {
                currentSongIndex--
            }
            updateUI()
            mediaPlayer.stop()
            mediaPlayer.release()
            mediaPlayer = MediaPlayer.create(this, songs[currentSongIndex].uri)
            mediaPlayer.start()
        }
        binding.nextSongButton.setOnClickListener {
            if(currentSongIndex > songs.size - 1) {
                currentSongIndex = 0
            } else {
                currentSongIndex++
            }
            updateUI()
            mediaPlayer.stop()
            mediaPlayer.release()
            mediaPlayer = MediaPlayer.create(this, songs[currentSongIndex].uri)
            mediaPlayer.start()
        }
    }

    private fun updateUI() {
        binding.tvSongName.text = songs[currentSongIndex].name

    }

    companion object {
        fun newIntent(context: Context) = Intent(context, ActivityTask5::class.java)
    }

}