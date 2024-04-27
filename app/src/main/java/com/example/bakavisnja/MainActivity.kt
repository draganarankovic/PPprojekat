package com.example.bakavisnja

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var btnUlogujSe: Button
    private lateinit var btnNoviNalog: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)




        btnUlogujSe = findViewById(R.id.ulogujse1)
        btnNoviNalog = findViewById(R.id.novinalog1)

        btnUlogujSe.setOnClickListener{
            val intent = Intent(this, WelcomeActivity::class.java)
            startActivity(intent)
        }

        btnNoviNalog.setOnClickListener{
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