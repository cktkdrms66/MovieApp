package com.weather.movieapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.weather.movieapplication.databinding.ActivityMainBinding
import com.weather.movieapplication.ui.MovieFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.activity = this

        supportFragmentManager.beginTransaction().replace(R.id.frameLayout, MovieFragment()).commit()

    }
}
