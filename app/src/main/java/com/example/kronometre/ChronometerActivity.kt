package com.example.kronometre

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.kronometre.databinding.ActivityChronometerBinding

class ChronometerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChronometerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityChronometerBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        enableEdgeToEdge()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var pauseTime:Long = 0
        binding.buttonStart.setOnClickListener {
            binding.chronometer.base = SystemClock.elapsedRealtime() + pauseTime    // elapsedRealTime, sistem saatini kronometreye aktarır
            binding.chronometer.start()
            binding.buttonStart.visibility = View.GONE      // visibility, bir görünüm olduğu için View kullanıldı
            binding.buttonPause.visibility = View.VISIBLE   // pause butonunu görünür yap
            binding.imageStart.setImageDrawable(getDrawable(R.drawable.pause))      // pause arkaplanını ayarla
        }

        binding.buttonPause.setOnClickListener {
            pauseTime = binding.chronometer.base - SystemClock.elapsedRealtime()
            binding.chronometer.stop()
            binding.buttonPause.visibility = View.GONE
            binding.buttonStart.visibility = View.VISIBLE
            binding.imageStart.setImageDrawable(getDrawable(R.drawable.start))

        }

        binding.buttonReset.setOnClickListener {
            binding.chronometer.base = SystemClock.elapsedRealtime()
            binding.chronometer.stop()
            pauseTime = 0
            binding.buttonPause.visibility = View.GONE
            binding.buttonStart.visibility = View.VISIBLE
            binding.imageStart.setImageDrawable(getDrawable(R.drawable.start))
        }
    }
}