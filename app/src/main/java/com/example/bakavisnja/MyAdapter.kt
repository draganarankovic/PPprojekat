package com.example.bakavisnja

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.media.Image
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.ContentView
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.findViewTreeViewModelStoreOwner
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.handleCoroutineException
import com.bumptech.glide.Glide;
import java.io.ByteArrayOutputStream


class MyAdapter(var context: Context, private val userList: ArrayList<Recept>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    private lateinit var nazivRecepta: TextView
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recept_item,
            parent, false)
        return MyViewHolder(itemView)

    }

    @SuppressLint("MissingInflatedId")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentitem = userList[position]

        holder.naziv.text = currentitem.naziv
        holder.koraci = currentitem.koraci
        holder.kategorija = currentitem.kategorija
        holder.sastojci = currentitem.sastojci
        holder.vreme = currentitem.vreme

        val storageRef = FirebaseStorage.getInstance()
            .getReferenceFromUrl("gs://bakavisnja-b4f26.appspot.com/")
            .child("User")
            .child("${currentitem.id}.jpg")

        val TEN_MEGABYTE: Long = 5 * 1024 * 1024
        storageRef.getBytes(TEN_MEGABYTE).addOnSuccessListener{
            bytes -> holder.slikaByte = bytes
        }.addOnFailureListener{
            Log.e(TAG, "Greška prilikom preuzimanja slike: ")
        }

        Glide.with(context)
            .load(storageRef)
            .into(holder.slika)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    inner class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val naziv : TextView = itemView.findViewById(R.id.naslov_recepta)
        val slika : ImageView = itemView.findViewById(R.id.slikaa)
        var kategorija: String? = "непознато"
        var koraci: String? = "непознато"
        var sastojci: String? = "непознато"
        var vreme: String? = "непознато"
        var slikaByte: ByteArray? = ByteArray(0)
        init {
            itemView.setOnClickListener {
                val intent = Intent(context, OtvorenReceptActivity::class.java)
                intent.putExtra("naziv", naziv.text)
                intent.putExtra("koraci", koraci)
                intent.putExtra("kategorija", kategorija)
                intent.putExtra("sastojci", sastojci)
                intent.putExtra("vreme", vreme)
                intent.putExtra("slika", slikaByte)
                context.startActivity(intent)
            }
        }
    }
}
