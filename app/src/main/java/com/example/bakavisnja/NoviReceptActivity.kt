package com.example.bakavisnja

import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Bitmap
import android.media.Image
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.bakavisnja.databinding.ActivityKuhinjaBinding
import com.example.bakavisnja.databinding.ActivityNoviReceptBinding
import com.google.android.gms.auth.api.signin.internal.Storage
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.util.UUID

class NoviReceptActivity : AppCompatActivity() {

    private lateinit var btnDodaj: Button
    private lateinit var binding: ActivityNoviReceptBinding
    private lateinit var mAuth : FirebaseAuth
    private lateinit var progressDialog: ProgressDialog
    private lateinit var btnSlika: Button
    private lateinit var btnDodajSliku: Button
    private lateinit var imSlika: ImageView
    private lateinit var selektovanaKategorija: String
    private lateinit var selektovanoVreme: String
    private lateinit var imgURL: String
    private var createTimestamp: Long = 0
    var fileUri: Uri? = null
    private var imerecepta = ""
    private var sastojci = ""
    private var koraci = ""
    private var odabrana = false

    val kategorije = arrayOf("слатко", "слано")
    val vreme = arrayOf("15-30 минута", "30-60 минута", "више од сат времена")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoviReceptBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        val spinner = findViewById<Spinner>(R.id.spinnerK)
        mAuth = FirebaseAuth.getInstance()
        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Молимо Вас сачекајте!")
        progressDialog.setCanceledOnTouchOutside(false)
        btnSlika = findViewById(R.id.kamerica)
        btnDodajSliku = findViewById(R.id.dodaj_sliku)
        imSlika = findViewById(R.id.slicica)
        createTimestamp = System.currentTimeMillis()

        btnSlika.setOnClickListener {
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT

            startActivityForResult(
                Intent.createChooser(intent,"Изаберите слику"),0
            )

 //           }
        }
        btnDodajSliku.setOnClickListener {
            if(fileUri != null){
               uploadImage()
                imgURL = fileUri.toString()
               }
           else{
               Toast.makeText(applicationContext,"Одаберите слику",Toast.LENGTH_SHORT).show()
            }
        }

        binding.novirecept.setOnClickListener {

            validateData()
        }

        val arrayAdapter = ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,kategorije)
        spinner.adapter = arrayAdapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                Toast.makeText(applicationContext, "Одабрана категорија је "+ kategorije[position], Toast.LENGTH_SHORT).show()
                selektovanaKategorija = spinner.getSelectedItem().toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }


        val spinnerV = findViewById<Spinner>(R.id.spinnerV)
        val arrayAdapterV = ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,vreme)
        spinnerV.adapter = arrayAdapterV
        spinnerV.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                Toast.makeText(applicationContext, "Одабрано време је "+ vreme[position], Toast.LENGTH_SHORT).show()
                selektovanoVreme = spinnerV.getSelectedItem().toString()

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

//
//        btnDodaj = findViewById(R.id.novirecept)
//
//        btnDodaj.setOnClickListener{
//            val intent = Intent(this, KuhinjaActivity::class.java)
//            startActivity(intent)
//        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 0 && resultCode == RESULT_OK && data != null && data.data != null){
            fileUri = data.data
            try {
                val bitmap: Bitmap = MediaStore.Images.Media.getBitmap(contentResolver,fileUri)
                imSlika.setImageBitmap(bitmap)
            }catch (e: Exception){
                Log.e("Exception" ,"Error" + e)
            }
        }
    }
    fun uploadImage(){
        if(fileUri != null){
            val progressDialog = ProgressDialog(this)
            progressDialog.setTitle("Учитавање...")
            progressDialog.setMessage("Сачекајте...")
            progressDialog.show()

            val ref: StorageReference = FirebaseStorage.getInstance().getReference("User/"+createTimestamp+".jpg")
            ref.putFile(fileUri!!).addOnSuccessListener {
                progressDialog.dismiss()
                Toast.makeText(applicationContext,"Слика је успешно учитана!",Toast.LENGTH_SHORT).show()
            }.addOnFailureListener{
                progressDialog.dismiss()
                Toast.makeText(applicationContext,"Слика је неуспешно учитана!",Toast.LENGTH_SHORT).show()
            }
        }
        odabrana = true
    }



    private fun validateData() {
        imerecepta = binding.ime2.text.toString().trim()
        sastojci = binding.sastojci.text.toString().trim()
        koraci = binding.koraci.text.toString().trim()

        if(imerecepta.isEmpty()) {
            Toast.makeText(this,"Молимо Вас, унесите име рецепта!",Toast.LENGTH_SHORT).show()
        }
        else if(odabrana == false){
            Toast.makeText(this,"Молимо Вас, додајте слику!",Toast.LENGTH_SHORT).show()
        }
        else if(sastojci.isEmpty()) {
            Toast.makeText(this,"Молимо Вас, унесите састојке!",Toast.LENGTH_SHORT).show()
        }
        else if(koraci.isEmpty()) {
            Toast.makeText(this,"Молимо Вас, унесите кораке!",Toast.LENGTH_SHORT).show()
        }
        else{
            addReceptFirebase()
        }
    }


    private fun addReceptFirebase() {
        progressDialog.show()


        val hashMap = HashMap<String,Any>()
        hashMap["id"] = createTimestamp.toString()
        hashMap["naziv"] = imerecepta
        hashMap["sastojci"] = sastojci
        hashMap["koraci"] = koraci
        hashMap["kategorija"] = selektovanaKategorija
        hashMap["vreme"] = selektovanoVreme
        hashMap["slika"] = imgURL

        hashMap["timestamp"] = createTimestamp
        hashMap["uid"] = "${mAuth.uid}"
        val ref = FirebaseDatabase.getInstance().getReference("Recept")
        ref.child("$createTimestamp")
            .setValue(hashMap)
            .addOnSuccessListener {
                progressDialog.dismiss()
                Toast.makeText(this,"Успешно сте додали рецепт у кухињу!",Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { e ->
                progressDialog.dismiss()
                Toast.makeText(this,"Рецепт не може да се дода у кухињу",Toast.LENGTH_SHORT).show()
            }
    }
}


