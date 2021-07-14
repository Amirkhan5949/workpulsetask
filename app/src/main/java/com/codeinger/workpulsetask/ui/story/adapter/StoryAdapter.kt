package com.codeinger.workpulsetask.ui.story.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codeinger.workpulsetask.databinding.StoryBinding
import com.codeinger.workpulsetask.databinding.StorylayoutBinding
import com.codeinger.workpulsetask.model.StoryModel

import java.text.SimpleDateFormat

class StoryAdapter(private var storylist: ArrayList<StoryModel>) :
    RecyclerView.Adapter<StoryAdapter.StoryAdapter_View>() {


    class StoryAdapter_View(var binding: StorylayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryAdapter_View {
        var binding: StorylayoutBinding =
            StorylayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StoryAdapter_View(binding)
    }

    override fun onBindViewHolder(holder: StoryAdapter_View, position: Int) {
        holder.binding.title.text = storylist[position].title
        holder.binding.by.text = storylist[position].by.toString()
         holder.binding.score.text = storylist[position].score.toString()
        holder.binding.type.text = storylist[position].type.toString()

        var pattern: String = "HH:mm:ss"
        var df: SimpleDateFormat = SimpleDateFormat(pattern)
        var todayAsString: String = df.format(storylist[position].time)
        holder.binding.time.text = todayAsString

    }


    override fun getItemCount(): Int {
        return storylist.size

    }


}