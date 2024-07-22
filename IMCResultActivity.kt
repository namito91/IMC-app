package com.sysarcomp.firstapp.imccalculator

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.sysarcomp.firstapp.R
import com.sysarcomp.firstapp.imccalculator.ImcCalculatorActivity.Companion.IMC_KEY

class IMCResultActivity : AppCompatActivity() {

    // declaracion de los componentes
    private lateinit var tvResult: TextView
    private lateinit var tvIMC: TextView
    private lateinit var tvDesc: TextView
    private lateinit var btnReCalc: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imcresult)
        val result: Double = intent.extras?.getDouble(IMC_KEY) ?: -1.0
        initComponents()
        initUI(result)
        initListeners()
    }

    private fun initListeners() {
        btnReCalc.setOnClickListener{ onBackPressed() }
    }

    private fun initUI(result: Double) {

        tvIMC.text = result.toString()

        when (result) {
            in 0.00..18.5 -> {
                tvResult.text = getString(R.string.title_bajo_peso)
                tvResult.setTextColor(ContextCompat.getColor(this, R.color.peso_bajo))
                tvDesc.text = getString(R.string.desc_bajo_peso)
            }

            in 18.5..24.9 -> {
                tvResult.text = getString(R.string.title_normal_peso)
                tvResult.setTextColor(ContextCompat.getColor(this, R.color.peso_normal))
                tvDesc.text = getString(R.string.desc_normal_peso)
            }

            in 24.9..29.9 -> {
                tvResult.text = getString(R.string.title_sobrepeso)
                tvResult.setTextColor(ContextCompat.getColor(this, R.color.peso_sobrepeso))
                tvDesc.text = getString(R.string.desc_sobrepeso)
            }

            in 30.0..99.9 -> {
                tvResult.text = getString(R.string.title_obesidad)
                tvResult.setTextColor(ContextCompat.getColor(this, R.color.peso_obesidad))
                tvDesc.text = getString(R.string.desc_obesidad)
            }

            else -> { // error
                tvIMC.text = "error"
                tvResult.text = "error"
                tvDesc.text = "error"
            }
        }
    }

    private fun initComponents() {
        tvResult = findViewById(R.id.tvResult)
        tvIMC = findViewById(R.id.tvIMC)
        tvDesc = findViewById(R.id.tvDesc)
        btnReCalc = findViewById(R.id.btnReCalc)
    }


}