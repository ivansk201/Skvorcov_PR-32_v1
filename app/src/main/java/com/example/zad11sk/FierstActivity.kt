package com.example.zad11sk

import android.os.Bundle
import android.content.Intent
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.content.SharedPreferences
import android.preference.PreferenceManager
import  androidx.appcompat.app.AppCompatActivity


private lateinit var loginEditText: EditText
private lateinit var passwordEditText: EditText
private lateinit var sharedPreferences: SharedPreferences

class FierstActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fierd)
        loginEditText = findViewById(R.id.login)
        passwordEditText = findViewById(R.id.password)
        val loginButton = findViewById<Button>(R.id.button)
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        val savedLogin = sharedPreferences.getString("login", "")
        val savedPassword = sharedPreferences.getString("password", "")
        loginButton.setOnClickListener {
            val login = loginEditText.text.toString()
            val password = passwordEditText.text.toString()
            if (login.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Пожалуйста, введите логин и пароль", Toast.LENGTH_SHORT).show()
            } else if (savedLogin.isNullOrEmpty() && savedPassword.isNullOrEmpty()) {
                val editor = sharedPreferences.edit()
                editor.putString("login", login)
                editor.putString("password", password)
                editor.apply()
                val intent = Intent(this, SecondActivity::class.java)
                startActivity(intent)
            } else if (login == savedLogin && password == savedPassword) {
                val intent = Intent(this, SecondActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Пароль или логин неправильный", Toast.LENGTH_SHORT).show()
            }
        }
    }
}



