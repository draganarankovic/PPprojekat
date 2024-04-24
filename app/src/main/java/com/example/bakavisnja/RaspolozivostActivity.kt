package com.example.bakavisnja

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class RaspolozivostActivity : AppCompatActivity() {

    private lateinit var btnJesam: Button
    private lateinit var btnNisam: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_raspolozivost)

        btnJesam = findViewById(R.id.dugme_jesam)
        btnNisam = findViewById(R.id.dugme_nisam)

        btnJesam.setOnClickListener{
            val intent = Intent(this, KuhinjaActivity::class.java)
            startActivity(intent)
        }
        btnNisam.setOnClickListener{
            val intent = Intent(this, NeraspolozenActivity::class.java)
            startActivity(intent)
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}