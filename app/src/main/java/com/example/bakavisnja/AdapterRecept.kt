package com.example.bakavisnja

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdapterRecept(private val userList: ArrayList<Recept>) : RecyclerView.Adapter<AdapterRecept.MyViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recept_item,
            parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
       return userList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentitem = userList[position]
       // holder.naziv.text = currentitem.naziv
       // holder.slika.text = currentitem.slika
    }

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
      //  val naziv: TextView = itemView.findViewById(R.id.naziv)
      //  val slika: TextView = itemView.findViewById(R.id.slika)

    }

}