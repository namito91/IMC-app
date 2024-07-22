package com.sysarcomp.firstapp.imccalculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.RangeSlider
import com.sysarcomp.firstapp.R
import java.text.DecimalFormat

class ImcCalculatorActivity : AppCompatActivity() {

    private var isMaleSelected: Boolean = true

    private var isFemaleSelected: Boolean = false

    private var currentWeight: Int = 75

    private var currentHeight: Int = 120

    private var currentAge: Int = 30


    // declaracion de los componentes
    private lateinit var viewMale: CardView

    private lateinit var viewFemale: CardView

    private lateinit var tvHeight: TextView

    private lateinit var rsHeight: RangeSlider

    private lateinit var tvWeight: TextView

    private lateinit var btnSubWeight: FloatingActionButton

    private lateinit var btnAddWeight: FloatingActionButton

    private lateinit var tvAge: TextView

    private lateinit var btnSubAge: FloatingActionButton

    private lateinit var btnAddAge: FloatingActionButton

    private lateinit var btnCalc: Button

    // permite compartir variables entre clases
    companion object {
        const val IMC_KEY = "IMC_RESULT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_imc_calculator)

        initComponents()

        initListeners()

        initUI()
    }


    private fun initComponents() {

        viewMale = findViewById(R.id.viewMale)

        viewFemale = findViewById(R.id.viewFemale)

        tvHeight = findViewById(R.id.tvHeight)

        rsHeight = findViewById(R.id.rsHeight)

        tvWeight = findViewById(R.id.tvWeight)

        btnAddWeight = findViewById(R.id.btnAddWeight)

        btnSubWeight = findViewById(R.id.btnSubWeight)

        tvAge = findViewById(R.id.tvAge)

        btnAddAge = findViewById(R.id.btnAddAge)

        btnSubAge = findViewById(R.id.btnSubAge)

        btnCalc = findViewById(R.id.btnCalc)

    }


    private fun initListeners() {

        viewMale.setOnClickListener {

            changeGender()

            setGenderCardColor()
        }

        viewFemale.setOnClickListener {

            changeGender()

            setGenderCardColor()
        }

        rsHeight.addOnChangeListener { _, value, _ ->
            // sacarle el .0
            val df = DecimalFormat("#.##")

            currentHeight = df.format(value).toInt()

            tvHeight.text = "$currentHeight cm"
        }

        btnAddWeight.setOnClickListener {

            currentWeight += 1

            setWeight()
        }

        btnSubWeight.setOnClickListener {

            currentWeight -= 1

            setWeight()
        }

        btnAddAge.setOnClickListener {

            currentAge += 1

            setAge()
        }

        btnSubAge.setOnClickListener {

            currentAge -= 1

            setAge()
        }

        btnCalc.setOnClickListener {

            val result = calculateIMC()

            navigateToResult(result)
        }
    }

    private fun navigateToResult(result: Double) {

        val intent = Intent(this, IMCResultActivity::class.java)

        intent.putExtra(IMC_KEY, result)

        startActivity(intent)
    }


    private fun calculateIMC(): Double {

        val df = DecimalFormat("#.##")

        // currentHeight.toDouble() / 100  -> pasa a decimal, y a metros
        var imc = currentWeight / ((currentHeight.toDouble() / 100) * (currentHeight.toDouble() / 100))

        imc = df.format(imc).toDouble()

        Log.i("patrox imc" , "$imc")

        return imc

//        Blanco = Bajo peso (IMC <18,5)
//        Amarillo = Rango normal (IMC = 18,5-24,9)
//        Naranja = Sobrepeso (IMC = 24.9-29,9)
//        Rojo = Obesidad (IMC >30)
//
        //  TO BE APPLIED !
        //        when(imc){
//
//            18.5 -> println("estas en un peso mas bajo de lo normal")
//            in 18.5 .. 24.9 -> println("estas en un peso normal")
//            in 24.9..29.9 -> println("estas en sobrepeso")
//            else -> println("estas en obesidad,cuidado!")
//        }

    }

    private fun setWeight() {
        // actualizar el valor en la vista
        tvWeight.text = currentWeight.toString();
    }

    private fun setAge() {
        // actualizar el valor en la vista
        tvAge.text = currentAge.toString();
    }

    private fun changeGender() {

        isMaleSelected = !isMaleSelected

        isFemaleSelected = !isFemaleSelected
    }


    private fun setGenderCardColor() {

        viewMale.setCardBackgroundColor(getBackgroundColor(isMaleSelected))

        viewFemale.setCardBackgroundColor(getBackgroundColor(isFemaleSelected))
    }


    private fun getBackgroundColor(isSelectedComponent: Boolean): Int {

        val colorReference = if (isSelectedComponent) {
            R.color.background_component_selected
        } else {
            R.color.background_component
        }

        return ContextCompat.getColor(this, colorReference)
    }


    private fun initUI() {

        setGenderCardColor()

        setWeight()

        setAge()
    }

}