package com.example.bakavisnja

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class WelcomeActivity : AppCompatActivity() {

    private lateinit var btnUlogujSe: Button
    private lateinit var txtRegistrujSe: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_welcome)

        btnUlogujSe = findViewById(R.id.ulogujse2)
        txtRegistrujSe = findViewById(R.id.registrujse1)

        btnUlogujSe.setOnClickListener{
            val intent = Intent(this, RaspolozivostActivity::class.java)
            startActivity(intent)
        }

       txtRegistrujSe.setOnClickListener{
            val intent = Intent(this, Singupctivity::class.java)
            startActivity(intent)
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}