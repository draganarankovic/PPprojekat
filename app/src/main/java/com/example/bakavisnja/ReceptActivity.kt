package com.example.bakavisnja

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.bakavisnja.databinding.ActivityReceptBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ReceptActivity : AppCompatActivity() {

    private lateinit var binding: ActivityReceptBinding

    private var receptID = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityReceptBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        receptID = intent.getStringExtra("naslov_recepta")!!

        ucitajRecept()


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun ucitajRecept() {
        val ref = FirebaseDatabase.getInstance().getReference("Recept")
        ref.child(receptID)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val kategorija = "${snapshot.child("kategorija").value}"
                    val koraci = "${snapshot.child("koraci").value}"
                    val naziv = "${snapshot.child("naziv").value}"
                    val sastojci = "${snapshot.child("sastojci").value}"
                    val slika = "${snapshot.child("slika").value}"
                    val vreme = "${snapshot.child("vreme").value}"

                    binding.kategorijaTV.text = kategorija
                    binding.koraciTV.text = koraci
                    binding.sastojciTV.text = sastojci
                    binding.vremeTV.text = vreme
                }



                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })
    }

}