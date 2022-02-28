package com.example.mvvm.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mvvm.databinding.ActivityMainBinding
import com.example.mvvm.network.DataRepository
import com.example.mvvm.network.DataService
import com.example.mvvm.utils.Status
import com.example.mvvm.viewmodel.DataViewModel
import com.example.mvvm.viewmodel.DataViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val service = DataService.getRetrofit()
    private val repository = DataRepository(service)
    private val viewModel: DataViewModel by lazy {
        ViewModelProvider(
            this,
            DataViewModelFactory(repository)
        )[DataViewModel::class.java]
    }

    private val dataAdapter = DataAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRefresh.setOnClickListener {
            viewModel.getData(
                page = 1
            )
        }

        binding.recyclerViewList.adapter = dataAdapter
        binding.recyclerViewList.layoutManager =
            GridLayoutManager(
                this,
                3
            )

        configureObservers()

        viewModel.getData(page = 1)
    }

    private fun configureObservers() {
        viewModel.statusLiveData.observe(this){
            when(it) {
                Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    binding.flBottom.visibility = View.VISIBLE
                    binding.recyclerViewList.visibility = View.VISIBLE
                    binding.tvErrorText.visibility =View.GONE
                }

                Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.flBottom.visibility = View.GONE
                    binding.recyclerViewList.visibility = View.GONE
                    binding.tvErrorText.visibility = View.GONE
                }

                Status.ERROR, null -> {
                    binding.progressBar.visibility = View.GONE
                    binding.flBottom.visibility = View.VISIBLE
                    binding.recyclerViewList.visibility = View.GONE
                    binding.tvErrorText.visibility = View.VISIBLE
                }
            }
        }
        viewModel.dataLiveData.observe(this){
            dataAdapter.setDataList(it)
        }
    }
}