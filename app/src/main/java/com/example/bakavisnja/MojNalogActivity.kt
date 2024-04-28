package com.example.bakavisnja

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth

class MojNalogActivity : AppCompatActivity() {

    private lateinit var btnOdjaviSe: Button
    private lateinit var mAuth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_moj_nalog2)

        btnOdjaviSe = findViewById(R.id.odjavise)
        mAuth = FirebaseAuth.getInstance()
        btnOdjaviSe.setOnClickListener{

            mAuth.signOut()
            val intent = Intent(this, MainActivity::class.java)
            finish()
            startActivity(intent)
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}