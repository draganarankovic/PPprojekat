package com.example.bakavisnja

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class KuhinjaActivity : AppCompatActivity() {

    private lateinit var btnMojNalog: Button
    private lateinit var btnNoviRecept: Button
    private lateinit var dbref: DatabaseReference
    private lateinit var listaSlanih: RecyclerView
    private lateinit var listaSlatkih: RecyclerView
    private lateinit var receptArrayListSlano: ArrayList<Recept>
    private lateinit var receptArrayListSlatko: ArrayList<Recept>

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_kuhinja)

        btnMojNalog = findViewById(R.id.mojnalog)
        btnNoviRecept = findViewById(R.id.novirecept)

        listaSlanih = findViewById(R.id.lista_slanih)
//        listaSlanih.layoutManager = LinearLayoutManager(this)
//        listaSlanih.setHasFixedSize(true)

        listaSlatkih = findViewById(R.id.lista_slatkih)
//        listaSlanih.layoutManager = LinearLayoutManager(this)
//        listaSlanih.setHasFixedSize(true)

        receptArrayListSlano = arrayListOf<Recept>()
        receptArrayListSlatko = arrayListOf<Recept>()

        getReceptData(this)

        btnMojNalog.setOnClickListener{
            val intent = Intent(this, MojNalogActivity::class.java)
            startActivity(intent)
        }
        btnNoviRecept.setOnClickListener{
            val intent = Intent(this, NoviReceptActivity::class.java)
            startActivity(intent)
        }

        listaSlanih.setOnClickListener{
            val intent = Intent(this, OtvorenReceptActivity::class.java)
            startActivity(intent)
        }
        //val adapter = MyAdapter(receptArrayList) // userList je lista recepata koju koristite
        //listaSlanih.adapter = adapter

        //Postavite LayoutManager ako je potrebno
        //listaSlanih.layoutManager = LinearLayoutManager(this)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun getReceptData(context: Context) {

        dbref = FirebaseDatabase.getInstance().getReference("Recept")
        dbref.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    for(receptSnapshot in snapshot.children) {
                        val recept = receptSnapshot.getValue(Recept::class.java)!!
                        if(recept.kategorija == "слано") {
                            receptArrayListSlano.add(recept!!)
                        }
                        else {
                            receptArrayListSlatko.add(recept!!)
                        }
                    }
                    listaSlanih.adapter = MyAdapter(context, receptArrayListSlano)
                    listaSlatkih.adapter = MyAdapter(context, receptArrayListSlatko)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}