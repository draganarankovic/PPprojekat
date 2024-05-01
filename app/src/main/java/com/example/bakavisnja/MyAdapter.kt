package com.example.bakavisnja

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.media.Image
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


class MyAdapter(var context: Context, private val userList: ArrayList<Recept>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    private lateinit var nazivRecepta: TextView
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recept_item,
            parent, false)
        return MyViewHolder(itemView)

    }


    @SuppressLint("MissingInflatedId")
//    fun getView(position: Int, contentView: View?, parent: ViewGroup): View{
//
//        val inflater : LayoutInflater = LayoutInflater.from(parent.context)
//        val view: View = inflater.inflate(R.layout.activity_recept, null)
//
//        val slika : ImageView = view.findViewById(R.id.slikaa)
//        val naziv : TextView = view.findViewById(R.id.naslov_recepta)
//        val kategorija : TextView = view.findViewById(R.id.kategorija)
//        val vreme : TextView = view.findViewById(R.id.vreme)
//        val sastojci : TextView = view.findViewById(R.id.sastojci)
//        val koraci : TextView = view.findViewById(R.id.koraci)
//
//        slika.setImageResource(userList[position].slika)
//        naziv.text = userList[position].naziv
//        kategorija.text = userList[position].kategorija
//        vreme.text = userList[position].vreme
//        sastojci.setText(userList[position].sastojci)
//        koraci.setText(userList[position].koraci)
//
//        return view
//    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentitem = userList[position]

        holder.naziv.text = currentitem.naziv
        holder.koraci = currentitem.koraci
        holder.kategorija = currentitem.kategorija
        holder.sastojci = currentitem.sastojci
        holder.vreme = currentitem.vreme

        val storageRef = FirebaseStorage.getInstance().reference.child("storage/bakavisnja-b4f26.appspot.com/files/${currentitem.slika}")
        storageRef.downloadUrl.addOnSuccessListener { uri ->
            holder.slika.setImageURI(uri)
        }
 //       holder.slika.setImageResource(currentitem.slika)
//        holder.kategorija.text = currentitem.kategorija
//        holder.koraci.text = currentitem.koraci
//        holder.sastojci.text = currentitem.sastojci
//        holder.vreme.text = currentitem.vreme

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

        init {
            itemView.setOnClickListener {
                val intent = Intent(context, OtvorenReceptActivity::class.java)
                intent.putExtra("naziv", naziv.text)
                intent.putExtra("koraci", koraci)
                intent.putExtra("kategorija", kategorija)
                intent.putExtra("sastojci", sastojci)
                intent.putExtra("vreme", vreme)
                context.startActivity(intent)
            }
        }

    }
}

private fun ImageView.setImageResource(slika: String?) {

}
