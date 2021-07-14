package com.codeinger.workpulsetask.ui.story

import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.codeinger.workpulsetask.R
import com.codeinger.workpulsetask.databinding.ActivityMainBinding
import com.codeinger.workpulsetask.model.StoryModel
import com.codeinger.workpulsetask.ui.story.adapter.StoryAdapter
import com.codeinger.workpulsetask.utils.ApiState
import com.codeinger.workpulsetask.utils.util
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var storyadapter: StoryAdapter
    private lateinit var binding: ActivityMainBinding

    private val storyViewModel: StoryViewModel by viewModels()

    private val list = arrayListOf<StoryModel>()

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerview()

        storyViewModel.getStorys()


        binding.swipeContainer.setOnRefreshListener {
            if (util.isNetworkAvailable(this))
                storyViewModel.getStorys()
            else {
                swipeContainer.isRefreshing = false
                Toast.makeText(
                    this,
                    getString(R.string.internet),
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        storyViewModel.postsLiveData.observe(this@MainActivity, {
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
                    list.clear()
                    list.addAll(it.data)
                    swipeContainer.isRefreshing = false
                    storyadapter.notifyDataSetChanged()
                }

                is ApiState.Empty -> {

                }
            }
        })


    }

    private fun initRecyclerview() {

        storyadapter = StoryAdapter(list)
        binding.rvItem.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = storyadapter
        }
    }


}