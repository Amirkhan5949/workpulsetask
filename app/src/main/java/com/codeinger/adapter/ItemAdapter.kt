package com.codeinger.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
 import androidx.recyclerview.widget.RecyclerView
import com.codeinger.workpulsetask.databinding.ItemBinding
import com.codeinger.workpulsetask.model.ItemsModel

class ItemAdapter(private var itemlist :List<ItemsModel>): RecyclerView.Adapter<ItemAdapter.ItemAdapter_View>() {

    private lateinit var binding: ItemBinding

    class ItemAdapter_View(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemAdapter_View {
        binding= ItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ItemAdapter_View(binding.root)
    }

    override fun onBindViewHolder(holder: ItemAdapter_View, position: Int) {
        binding.title.text= itemlist[position].title
        binding.body.text= itemlist[position].id.toString()
    }

    override fun getItemCount(): Int {
        return itemlist.size
    }

    fun setData (list : List<ItemsModel>){
        this.itemlist=list
        notifyDataSetChanged()
    }
}