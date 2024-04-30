package com.example.bakavisnja

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Window
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.bakavisnja.databinding.ActivityMojNalog2Binding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.io.File

class MojNalogActivity : AppCompatActivity() {

    private lateinit var btnOdjaviSe: Button
    private lateinit var binding: ActivityMojNalog2Binding
    private lateinit var mAuth : FirebaseAuth
    private lateinit var dbref: DatabaseReference
    private lateinit var userList: ArrayList<User>
    private lateinit var storageReference: StorageReference
    private lateinit var dialog: Dialog
    private lateinit var user : User
    private lateinit var uid: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_moj_nalog2)

        binding = ActivityMojNalog2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        btnOdjaviSe = findViewById(R.id.odjavise)
        mAuth = FirebaseAuth.getInstance()

        uid = mAuth.currentUser?.uid.toString()

        dbref = FirebaseDatabase.getInstance().getReference("user")
        if(uid.isNotEmpty()){
            Toast.makeText(applicationContext,"$uid", Toast.LENGTH_SHORT).show()
            getUserData()
        }
        else {
           Toast.makeText(applicationContext,"nije uslo!",Toast.LENGTH_SHORT).show()
        }

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

    private fun getUserData() {

//        showProgressBar()
        dbref = FirebaseDatabase.getInstance().getReference("user")
        dbref.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                if(snapshot.exists()){
                    for(userSnapshot in snapshot.children) {
                        val user = userSnapshot.getValue(User::class.java)!!
                        if(user.uid == uid){

                            binding.ime2.setText(user.korisnik)
                            binding.mejl2.setText(user.email)

                        }

                    }

                }
            }

           override fun onCancelled(error: DatabaseError) {
//               hideProgressBar()
           }
       })
    }

//    private fun getUserProfile(){
//        storageReference = FirebaseStorage.getInstance().reference.child("User/$uid.jpg")
//        val localFile = File.createTempFile("tempImage", "jpg")
//        storageReference.getFile(localFile).addOnSuccessListener{
//
//        }.addOnFailureListener{
//
//        }
//
//    }
//    private fun showProgressBar(){
//        dialog = Dialog(this@MojNalogActivity)
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
//        dialog.setContentView(R.layout.activity_moj_nalog2)
//        dialog.setCanceledOnTouchOutside(false)
//        dialog.show()
//    }
//
//    private fun hideProgressBar(){
//        dialog.dismiss()
//    }
}