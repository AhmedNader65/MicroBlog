package com.shahry.microblogging.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.shahry.microblogging.R
import com.shahry.microblogging.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setTheme(R.style.MicroBlogging)
        setContentView(binding.root)
    }
}