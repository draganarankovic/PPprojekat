package com.example.bakavisnja

import android.graphics.BitmapFactory
import android.os.Bundle
import android.text.Editable
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.ListView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.bakavisnja.databinding.ActivityOtvorenReceptBinding
import com.google.firebase.storage.FirebaseStorage

class OtvorenReceptActivity : AppCompatActivity() {

    private lateinit var userArrayList : ArrayList<Recept>
    private lateinit var txtNaslov: TextView
    private lateinit var txtKoraci: EditText
    private lateinit var txtSastojci: EditText
    private lateinit var txtVreme: TextView
    private lateinit var txtKategorija: TextView
    private lateinit var imgSlika: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_otvoren_recept)


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        txtNaslov = findViewById(R.id.naslov_recepta)
        txtKoraci = findViewById(R.id.koraci)
        txtSastojci = findViewById(R.id.sastojci)
        txtVreme = findViewById(R.id.vreme)
        txtKategorija = findViewById(R.id.kategorija)
        imgSlika = findViewById(R.id.slikaa)

        txtNaslov.text  = getIntent().getStringExtra("naziv")
        txtKoraci.setText(getIntent().getStringExtra("koraci"))
        txtSastojci.setText(getIntent().getStringExtra("sastojci"))
        txtVreme.text = getIntent().getStringExtra("vreme")
        txtKategorija.text = getIntent().getStringExtra("kategorija")

        val bytearray = getIntent().getByteArrayExtra("slika")
        if(bytearray != null) {
            val bitmap = BitmapFactory.decodeByteArray(bytearray, 0, bytearray!!.size)
            imgSlika.setImageBitmap(bitmap)
        }
    }
}