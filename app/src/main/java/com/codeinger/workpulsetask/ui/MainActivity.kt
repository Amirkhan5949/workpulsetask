package com.codeinger.workpulsetask.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.codeinger.adapter.ItemAdapter
import com.codeinger.utils.ApiState
import com.codeinger.viewmodel.ItemViewModel
import com.codeinger.workpulsetask.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var itemadapter: ItemAdapter
    private lateinit var binding: ActivityMainBinding

    private val itemViewModel: ItemViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerview()

        itemViewModel.getPost()


        lifecycleScope.launchWhenStarted {

            itemViewModel.postsLiveData.observe(this@MainActivity, {
                when (it) {

                    is ApiState.Loading -> {
                        binding.rvItem.isVisible = false
                        binding.progressBar.isVisible = true
                    }

                    is ApiState.Failure -> {
                        binding.rvItem.isVisible = false
                        binding.progressBar.isVisible = false
                    }
                    is ApiState.Success -> {
                        binding.rvItem.isVisible = true
                        binding.progressBar.isVisible = false
                        itemadapter.setData(it.data)
                        itemadapter.notifyDataSetChanged()
                    }

                    is ApiState.Empty -> {

                    }
                }
            })


        }



    }

    private fun initRecyclerview() {

        itemadapter = ItemAdapter(ArrayList())
        binding.rvItem.apply {

            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = itemadapter
        }
    }
}