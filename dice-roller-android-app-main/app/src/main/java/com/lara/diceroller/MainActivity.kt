package com.lara.diceroller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lara.diceroller.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.rollBtn.text = "Let's Roll"

        binding.rollBtn.setOnClickListener {
            rollDice()
        }
    }

    private fun rollDice() {
        val randomInt = Random().nextInt(6) + 1
        binding.resultText.text = "$randomInt"
    }

}