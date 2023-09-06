package com.example.zad11sk

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import java.math.BigDecimal
import java.math.RoundingMode

class SecondActivity : AppCompatActivity() {
    private lateinit var imageView2: ImageView
    private lateinit var radiusEditText: EditText
    private lateinit var aEditText: EditText
    private lateinit var bEditText: EditText
    private lateinit var calculateButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        imageView2 = findViewById(R.id.imageView2)
        radiusEditText = findViewById(R.id.radiusEditText)
        radiusEditText.inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL
        aEditText = findViewById(R.id.aEditText)
        aEditText.inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL
        bEditText = findViewById(R.id.bEditText)
        bEditText.inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL
        calculateButton = findViewById(R.id.calculateButton)
        val shapeSpinner = findViewById<Spinner>(R.id.shapeSpinner)
        shapeSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedShape = shapeSpinner.selectedItem.toString()
                radiusEditText.visibility = View.GONE
                aEditText.visibility = View.GONE
                bEditText.visibility = View.GONE
                if (selectedShape == "Выберите фигуру") {
                    imageView2.visibility = View.GONE
                } else {
                    when (selectedShape) {
                        "Круг" -> {
                            radiusEditText.visibility = View.VISIBLE
                            imageView2.setImageResource(R.drawable.formula1)
                        }
                        "Треугольник" -> {
                            aEditText.visibility = View.VISIBLE
                            bEditText.visibility = View.VISIBLE
                            imageView2.setImageResource(R.drawable.formula2)
                        }
                    }
                    imageView2.visibility = View.VISIBLE
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                imageView2.visibility = View.GONE
            }
        }
        calculateButton.setOnClickListener {
            val selectedShape = shapeSpinner.selectedItem.toString()
            var resultText = ""
            when (selectedShape) {
                "Круг" -> {
                    val perimeterText = radiusEditText.text.toString()
                    if (perimeterText.isNotEmpty() && perimeterText.toDoubleOrNull() != null) {
                        val perimeter = perimeterText.toDouble()
                        val radius = perimeter / (2 * Math.PI)
                        val roundedRadius = BigDecimal(radius).setScale(5, RoundingMode.HALF_UP).toDouble()
                        resultText = "Радиус круга: $roundedRadius"
                    }
                }
                "Треугольник" -> {
                    val aText = aEditText.text.toString()
                    val bText = bEditText.text.toString()
                    if (aText.isNotEmpty() && aText.toDoubleOrNull() != null &&
                        bText.isNotEmpty() && bText.toDoubleOrNull() != null
                    ) {
                        val a = aText.toDouble()
                        val b = bText.toDouble()
                        val perimeter = a + b + Math.sqrt(a * a + b * b)
                        val roundedPerimeter = BigDecimal(perimeter).setScale(5, RoundingMode.HALF_UP).toDouble()
                        resultText = "Периметр треугольника: $roundedPerimeter"
                    }
                }
            }
            val intent = Intent(this, FierdActivity::class.java)
            intent.putExtra("shape", selectedShape)
            intent.putExtra("result", resultText)
            startActivity(intent)
            }
        }
    }
