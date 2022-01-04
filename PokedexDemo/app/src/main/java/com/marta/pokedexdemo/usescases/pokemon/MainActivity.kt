package com.marta.pokedexdemo.usescases.pokemon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.marta.pokedexdemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}