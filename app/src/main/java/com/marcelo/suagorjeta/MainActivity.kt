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
            val vConta = binding.edtValorConta.text
            val nPessoas = binding.edtTotalPessoas.text
            if (vConta?.isEmpty() == true || nPessoas?.isEmpty() == true) {
                Snackbar.make(binding.main, "Preencha todos os campos", Snackbar.LENGTH_SHORT).show()
            } else {
                val valorConta: Float = binding.edtValorConta.text.toString().toFloat()
                val nPessoas: Int = binding.edtTotalPessoas.text.toString().toInt()
                val valorTemporario = valorConta / nPessoas
                val porcentagemGorjeta = (valorTemporario * percentual) / 100
                val valorFinal = valorTemporario + porcentagemGorjeta
                binding.tvResultado.text = getString(R.string.label_result, valorFinal.toString())
            }
        }

        binding.btnLimpar.setOnClickListener {
            binding.edtValorConta.setText("")
            binding.edtTotalPessoas.setText("")
            binding.tvResultado.setText("")
            binding.rbOpcao1.isChecked = false
            binding.rbOpcao2.isChecked = false
            binding.rbOpcao3.isChecked = false
        }

    }
}