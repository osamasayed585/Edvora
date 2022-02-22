package com.edvora.edvora.ui.main

import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.TableLayout
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import com.edvora.edvora.R
import com.edvora.edvora.databinding.ActivityMainBinding
import com.edvora.edvora.ui.adapter.ViewPagerAdapter
import com.edvora.edvora.ui.upcoming.UpcomingViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.*
import com.google.android.material.tabs.TabLayoutMediator

val tabsArray = arrayOf(
    "nearest",
    "Upcoming(4)",
    "Past(2)"
)

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]


        val adapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
        binding.viewPager.adapter = adapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = tabsArray[position]
        }.attach()

        binding.filters.setOnClickListener {
            if (binding.filtersDialog.isVisible) {
                binding.filtersDialog.visibility = GONE
            } else {
                binding.filtersDialog.visibility = VISIBLE
            }
        }

        mainViewModel.getAllData(this)
        mainViewModel.fetchAllData.observe(this, {
            var cities: List<String> = emptyList()
            for (item in it) {
                cities += item.city
            }

            val cityList: ArrayAdapter<String> =
                ArrayAdapter(this, android.R.layout.simple_list_item_1, cities)
            binding.city.setAdapter(cityList)
        })

        mainViewModel.fetchAllData.observe(this, {
            var states: List<String> = emptyList()

            for (item in it) {
                states += item.state
            }

            val stateList: ArrayAdapter<String> =
                ArrayAdapter(this, android.R.layout.simple_list_item_1, states)
            binding.state.setAdapter(stateList)
        })

        binding.state.setOnItemClickListener { parent, _, position, _ ->
            binding.state.setText("Hello")

        }
    }
}