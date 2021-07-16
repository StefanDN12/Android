package com.example.viewmodelrecycler.Adapter.viewHolder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.viewmodelrecycler.R

class numeroViewHolder(view : View): RecyclerView.ViewHolder(view) {
    val numero = view.findViewById<TextView>(R.id.txtNumero)



    fun bind (item : Int){
        numero.text = item.toString()
    }
}