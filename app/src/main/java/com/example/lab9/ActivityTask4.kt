package com.example.lab9

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.view.animation.AccelerateInterpolator
import androidx.appcompat.app.AppCompatActivity
import com.example.lab9.databinding.ActivityTask4Binding
import com.plattysoft.leonids.ParticleSystem


class ActivityTask4 : AppCompatActivity() {

    private val binding by lazy { ActivityTask4Binding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.container.setOnTouchListener { _, event -> handleTouch(event) }
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

        val mParticleSystem = ParticleSystem(this, 100, R.drawable.circle, 10000)

        mParticleSystem.setSpeedModuleAndAngleRange(0.5f, 1f, 225, 305)

        mParticleSystem.setAcceleration(0.005f, 90)

        mParticleSystem.emit(x.toInt(), y.toInt(), 50, 1000)

    }

    companion object  {
        fun newIntent(context: Context) = Intent(context, ActivityTask4::class.java)
    }


}