package com.example.bakavisnja

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class KuhinjaActivity : AppCompatActivity() {

    private lateinit var btnMojNalog: Button
    private lateinit var btnNoviRecept: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_kuhinja)

        btnMojNalog = findViewById(R.id.mojnalog)
        btnNoviRecept = findViewById(R.id.novirecept)

        btnMojNalog.setOnClickListener{
            val intent = Intent(this, MojNalogActivity::class.java)
            startActivity(intent)
        }
        btnNoviRecept.setOnClickListener{
            val intent = Intent(this, NoviReceptActivity::class.java)
            startActivity(intent)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}