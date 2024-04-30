package com.example.bakavisnja

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapterUser(private val userList: ArrayList<User>) : RecyclerView.Adapter<MyAdapterUser.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapterUser.MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recept_item,
            parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyAdapterUser.MyViewHolder, position: Int) {
        val currentitem = userList[position]

        holder.korisnickoime.text = currentitem.korisnik
        holder.mejl.text = currentitem.email
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val korisnickoime : TextView = itemView.findViewById(R.id.ime2)
        val mejl : TextView = itemView.findViewById(R.id.mejl2)

    }
}
