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
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.auth.User

class Singupctivity : AppCompatActivity() {

    private lateinit var btnRegistrujSe: Button
    private lateinit var txtUlogujSe: TextView
    private  lateinit var edtKorisnik: EditText
    private  lateinit var edtSifra: EditText
    private lateinit var edtEmail: EditText
    private lateinit var mAuth : FirebaseAuth




    private lateinit var mDbRef: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        mAuth = FirebaseAuth.getInstance()

        setContentView(R.layout.activity_singupctivity)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        txtUlogujSe = findViewById(R.id.ulogujse3)
        btnRegistrujSe = findViewById(R.id.registrujse3)
        edtKorisnik = findViewById(R.id.korisnickoime2)
        edtEmail = findViewById(R.id.mejl)
        edtSifra = findViewById(R.id.sifra2)

        txtUlogujSe.setOnClickListener{
            val intent = Intent(this, WelcomeActivity::class.java)
            startActivity(intent)
        }
        btnRegistrujSe.setOnClickListener{
            val email = edtEmail.text.toString()
            val korisnik = edtKorisnik.text.toString()
            val sifra = edtSifra.text.toString()

            signUp(korisnik,email,sifra)
        }
    }

    private fun signUp(korisnik: String, email: String, sifra: String){
        mAuth.createUserWithEmailAndPassword(email, sifra)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                  //  mAuth.currentUser?.let {addUsertoDB(korisnik,email,it.uid)}
                  addUserTobase(korisnik,email,mAuth.currentUser?.uid!!)
                  val intent = Intent(this@Singupctivity, RaspolozivostActivity::class.java)
                  finish()
                  startActivity(intent)
                } else {
                    Toast.makeText(this@Singupctivity,"Покушајте поново!",Toast.LENGTH_SHORT).show()
                }
            }
    }

   private fun addUserTobase(korisnik: String, email: String, uid: String) {
       mDbRef = FirebaseDatabase.getInstance().getReference()
       mDbRef.child("user").child(uid).setValue(User(korisnik, email, uid))

//        val user = User(korisnik, email, uid)
//        db.collection("users").document(uid)
//            .set(user)
//            .addOnSuccessListener { documentReference ->
//                Log.d(TAG, "DocumentSnapshot added with ID: ${uid}")
//             jump to main activity
//
//                val intent = Intent(this@SigupActavity, MainActivity::class.java)
//                finish()
//                startActivity(intent)
//            }
//            .addOnFailureListener { e ->
//                Toast.makeText(this@SigupActavity, "Error -" + e.message, Toast.LENGTH_SHORT).show()
//            }
    }
}