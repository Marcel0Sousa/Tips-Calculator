package com.marcelo.suagorjeta

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView.Adapter
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

        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.num_pessoas,
            android.R.layout.simple_spinner_item
        )

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spNumPessoas.adapter = adapter

        var numPessoas = 0
        binding.spNumPessoas.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                numPessoas = position
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }

        binding.btnCalcular.setOnClickListener {
            val vConta = binding.edtValorConta.text
            if (vConta?.isEmpty() == true) {
                Snackbar.make(binding.main, "Preencha todos os campos", Snackbar.LENGTH_SHORT).show()
            } else {
                val valorConta: Float = binding.edtValorConta.text.toString().toFloat()
                val nPessoas: Int = numPessoas
                val valorTemporario = valorConta / nPessoas
                val porcentagemGorjeta = (valorTemporario * percentual) / 100
                val valorFinal = valorTemporario + porcentagemGorjeta
                binding.tvResultado.text = getString(R.string.label_result, valorFinal.toString())
            }
        }

        binding.btnLimpar.setOnClickListener {
            binding.edtValorConta.setText("")
            binding.tvResultado.setText("")
            binding.rbOpcao1.isChecked = false
            binding.rbOpcao2.isChecked = false
            binding.rbOpcao3.isChecked = false
        }

    }
}