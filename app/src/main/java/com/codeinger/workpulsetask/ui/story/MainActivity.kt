package com.codeinger.workpulsetask.ui.story

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.codeinger.workpulsetask.ui.story.adapter.ItemAdapter
import com.codeinger.workpulsetask.utils.ApiState
import com.codeinger.workpulsetask.databinding.ActivityMainBinding
import com.codeinger.workpulsetask.model.ItemsModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var itemadapter: ItemAdapter
    private lateinit var binding: ActivityMainBinding

    private val itemViewModel: ItemViewModel by viewModels()

    private val list = arrayListOf<ItemsModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerview()

//        itemViewModel.getPost("print=pretty")
        itemViewModel.getStorys()

        itemViewModel.postsLiveData.observe(this@MainActivity, {
            when (it) {
                is ApiState.Loading -> {
                    binding.rvItem.isVisible = false
                    binding.progressBar.isVisible = true
                }

                is ApiState.Failure -> {
                    binding.rvItem.isVisible = false
                    binding.progressBar.isVisible = false
                    Log.d("dsfsfss", "fail: "+it.msg.toString())

                }
                is ApiState.Success -> {
                    binding.rvItem.isVisible = true
                    binding.progressBar.isVisible = false
                    Log.d("dsfsfss", "onCreate: "+it.data.toString())
                    list.addAll(it.data)
                    itemadapter.notifyDataSetChanged()
                }

                is ApiState.Empty -> {

                }
            }
        })




    }

    private fun initRecyclerview() {

        itemadapter = ItemAdapter(list)
        binding.rvItem.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = itemadapter
        }
    }
}