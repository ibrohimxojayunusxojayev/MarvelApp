package com.example.marvelapp.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.marvelapp.R
import com.example.marvelapp.model.Characters

class CharacterAdapter (var list: MutableList<Characters>, var mypost: MyPost):
    RecyclerView.Adapter<CharacterAdapter.MyHolder>()  {

    class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var imgapl = itemView.findViewById<ImageView>(R.id.characterimg)
        var textapl = itemView.findViewById<TextView>(R.id.charctername)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        var myHolder =
            MyHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_characters, parent, false)
            )

        return myHolder
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        var obj = list.get(position)
        holder.imgapl.load(obj.imageurl) {
            placeholder(R.drawable.ic_launcher_background)
        }
        holder.textapl.text = obj.name

        holder.itemView.setOnClickListener {
            mypost.onItemClick(obj)
        }
    }
    interface MyPost {
        fun onItemClick(characters: Characters)
    }
}