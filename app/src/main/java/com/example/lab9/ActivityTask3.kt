package com.example.lab9


import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import com.example.lab9.databinding.ActivityTask3Binding
import com.plattysoft.leonids.ParticleSystem

class ActivityTask3 : AppCompatActivity() {

    private val binding by lazy { ActivityTask3Binding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.container.setOnTouchListener { _, event ->
            handleTouch(event) }
    }

    private fun handleTouch(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                createParticle(event.x, event.y)
            }
        }
        return true
    }



    private fun createParticle(x: Float, y: Float) {
        ParticleSystem(this, 100, R.drawable.circle, 1000)
            .setSpeedRange(0.2f, 0.5f)
            .emit(x.toInt(), y.toInt(), 50, 1000)
    }


    companion object  {
        fun newIntent(context: Context) = Intent(context, ActivityTask3::class.java)
    }

}