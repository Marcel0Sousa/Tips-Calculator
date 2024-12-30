package com.marcelo.suagorjeta

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.marcelo.suagorjeta.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        var percentual: Int = 0
        binding.rbOpcao1.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) percentual = 10
        }

        binding.rbOpcao2.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) percentual = 15
        }

        binding.rbOpcao3.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) percentual = 20
        }

        binding.btnCalcular.setOnClickListener {
            val valorConta: Float = binding.edtValorConta.text.toString().toFloat()
            val nPessoas: Int = binding.edtTotalPessoas.text.toString().toInt()

            val valorTemporario = valorConta / nPessoas
            val porcentagemGorjeta = (valorTemporario * percentual) / 100
            val valorFinal = valorTemporario + porcentagemGorjeta

            binding.tvResultado.text = getString(R.string.label_result, valorFinal.toString())
        }

    }
}