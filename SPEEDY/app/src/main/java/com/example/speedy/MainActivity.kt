package com.example.speedy

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.renderscript.ScriptGroup.Binding
import android.view.inputmethod.InputBinding
import com.example.speedy.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity()
{

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.weightPicker.minValue = 30
        binding.weightPicker.maxValue = 150

        binding.heightPicker.minValue = 100
        binding.heightPicker.maxValue = 250

        binding.weightPicker.setOnValueChangedListener{ _,_,_ ->
            calculateSpeedy()
        }
    }
    private fun calculateSpeedy()
    {
        val height = binding.heightPicker.value
        val doubleheight = height.toDouble() / 100

        val weight = binding.weightPicker.value

        val speedy = weight.toDouble() / (doubleheight * doubleheight)

        binding.resultsTV.text = String.format("Tu indice de masa corporal es: %.2f", speedy)
        binding.healthyTV.text = String.format("Tu peso es: %s", healthyMessage(speedy))
    }

    private fun healthyMessage(speedy: Double): String
    {
        if(speedy < 18.5)
            return "Bajo peso"

        if(speedy < 25.0)
            return "Saludable"

        if(speedy < 30.0)
            return "Sobrepeso"

        return "Obesidad"
    }
}