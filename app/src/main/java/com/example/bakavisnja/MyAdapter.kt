package com.example.bakavisnja

import android.content.Intent
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.ContentView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private val userList: ArrayList<Recept>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter.MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recept_item,
            parent, false)
        return MyViewHolder(itemView)
    }

    fun getView(position: Int, contentView: View?, parent: ViewGroup): View{

        val inflater : LayoutInflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.activity_otvoren_recept, null)

        val slika : ImageView = view.findViewById(R.id.slikaa)
        val naziv : TextView = view.findViewById(R.id.naslov_recepta)
        val kategorija : TextView = view.findViewById(R.id.kategorija)
        val vreme : TextView = view.findViewById(R.id.vreme)
        val sastojci : EditText = view.findViewById(R.id.sastojci)
        val koraci : EditText = view.findViewById(R.id.koraci)

        slika.setImageResource(userList[position].slika)
        naziv.text = userList[position].naziv
        kategorija.text = userList[position].kategorija
        vreme.text = userList[position].vreme
        sastojci.setText(userList[position].sastojci)
        koraci.setText(userList[position].koraci)

        return view
    }
    override fun onBindViewHolder(holder: MyAdapter.MyViewHolder, position: Int) {
        val currentitem = userList[position]

        holder.naziv.text = currentitem.naziv
        holder.slika.setImageResource(currentitem.slika)
//        holder.kategorija.text = currentitem.kategorija
//        holder.koraci.text = currentitem.koraci
//        holder.sastojci.text = currentitem.sastojci
//        holder.vreme.text = currentitem.vreme

    }

    override fun getItemCount(): Int {
        return userList.size
    }

    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val naziv : TextView = itemView.findViewById(R.id.naslov_recepta)
        val slika : ImageView = itemView.findViewById(R.id.slikaa)
//        val kategorija: TextView = itemView.findViewById(R.id.kategorija)
//        val koraci: EditText = itemView.findViewById(R.id.koraci)
//        val sastojci: EditText = itemView.findViewById(R.id.sastojci)
//        val vreme: TextView = itemView.findViewById(R.id.vreme)

    }
}

private fun ImageView.setImageResource(slika: String?) {

}
