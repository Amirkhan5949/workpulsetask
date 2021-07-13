package com.codeinger.workpulsetask.ui.story.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codeinger.workpulsetask.databinding.ItemBinding
import com.codeinger.workpulsetask.model.ItemsModel

import java.text.SimpleDateFormat

class ItemAdapter(private var itemlist: ArrayList<ItemsModel>) : RecyclerView.Adapter<ItemAdapter.ItemAdapter_View>() {



    class ItemAdapter_View(var binding: ItemBinding) : RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemAdapter_View {
        var binding: ItemBinding = ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemAdapter_View(binding)
    }

    override fun onBindViewHolder(holder: ItemAdapter_View, position: Int) {
        holder.binding.title.text = itemlist[position].title
        holder.binding.by.text = itemlist[position].by.toString()
        holder.binding.descendants.text = itemlist[position].descendants.toString()
        holder.binding.score.text = itemlist[position].score.toString()
        holder.binding.type.text = itemlist[position].type.toString()

        var pattern: String = "HH:mm:ss"
        var df: SimpleDateFormat = SimpleDateFormat(pattern)
        var todayAsString: String = df.format(itemlist[position].time)
        holder.binding.time.text = todayAsString

    }

    override fun getItemCount(): Int {
        return itemlist.size
    }


}