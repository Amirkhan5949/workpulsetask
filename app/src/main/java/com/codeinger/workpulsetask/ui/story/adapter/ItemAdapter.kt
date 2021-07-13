package com.codeinger.workpulsetask.ui.story.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codeinger.workpulsetask.databinding.ItemBinding
import com.codeinger.workpulsetask.model.ItemsModel

import java.text.SimpleDateFormat

class ItemAdapter(private var itemlist: ArrayList<ItemsModel>) : RecyclerView.Adapter<ItemAdapter.ItemAdapter_View>() {

    private lateinit var binding: ItemBinding

    class ItemAdapter_View(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemAdapter_View {
        binding = ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemAdapter_View(binding.root)
    }

    override fun onBindViewHolder(holder: ItemAdapter_View, position: Int) {
        binding.title.text = itemlist[position].title
        binding.by.text = itemlist[position].by.toString()
        binding.descendants.text = itemlist[position].descendants.toString()
        binding.score.text = position.toString()
        binding.type.text = itemlist[position].type.toString()

        var pattern: String = "HH:mm:ss"
        var df: SimpleDateFormat = SimpleDateFormat(pattern)
        var todayAsString: String = df.format(itemlist[position].time)
        binding.time.text = todayAsString

    }

    override fun getItemCount(): Int {
        return itemlist.size
    }


}