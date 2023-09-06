package com.example.zad11sk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText

class FierdActivity : AppCompatActivity() {

    private lateinit var shapeEditText: EditText
    private lateinit var resultEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fierst)

        shapeEditText = findViewById(R.id.edittext1)
        resultEditText = findViewById(R.id.edittext2)


        val intent = intent
        val shape = intent.getStringExtra("shape")
        val result = intent.getStringExtra("result")


        shapeEditText.setText(shape)
        resultEditText.setText(result)
    }
}