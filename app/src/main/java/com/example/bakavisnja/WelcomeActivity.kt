package com.example.bakavisnja

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth

class WelcomeActivity : AppCompatActivity() {

    private  lateinit var edtEmail: EditText
    private  lateinit var edtSifra: EditText
    private lateinit var btnUlogujSe: Button
    private lateinit var txtRegistrujSe: TextView
    private lateinit var mAuth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_welcome)
        mAuth = FirebaseAuth.getInstance()
        btnUlogujSe = findViewById(R.id.ulogujse2)
        txtRegistrujSe = findViewById(R.id.registrujse1)
        edtEmail = findViewById(R.id.korisnickoime1)
        edtSifra = findViewById(R.id.sifra1)
        

       txtRegistrujSe.setOnClickListener{
            val intent = Intent(this, Singupctivity::class.java)
            startActivity(intent)
        }
        btnUlogujSe.setOnClickListener {
            val email = edtEmail.text.toString()
            val sifra = edtSifra.text.toString()

            if(email.isNotEmpty() && sifra.isNotEmpty()) {
                login(email,sifra)
            }
            else {
                Toast.makeText(this@WelcomeActivity,"Нисте унели корисничко име или шифру, покушајте поново!",Toast.LENGTH_SHORT).show()

            }


        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun login(email: String,sifra: String){
        mAuth.signInWithEmailAndPassword(email, sifra)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val intent = Intent(this@WelcomeActivity, RaspolozivostActivity::class.java)
                    startActivity(intent)
                } else {
                   Toast.makeText(this@WelcomeActivity,"Неисправан мејл или шифра. Покушајте поново!",Toast.LENGTH_SHORT).show()


                }
            }
    }
}