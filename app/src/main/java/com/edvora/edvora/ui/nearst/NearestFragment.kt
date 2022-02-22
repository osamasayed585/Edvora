package com.edvora.edvora.ui.nearst

import android.opengl.Visibility
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.edvora.edvora.databinding.FragmentNearestBinding
import com.edvora.edvora.pojo.Ride
import com.edvora.edvora.ui.adapter.GenerallyAdapter
import com.edvora.edvora.ui.main.MainActivity


class NearestFragment :
    Fragment(),
    GenerallyAdapter.OnClickGenerallyItemListener,
    GenerallyAdapter.OnClickStateNameListener,
    GenerallyAdapter.OnClickCityNameListener{

    private var _binding: FragmentNearestBinding? = null
    private val binding get() = _binding!!
    private val listAdapter: GenerallyAdapter by lazy { GenerallyAdapter() }

    private lateinit var nearestViewModel: NearestViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        nearestViewModel = ViewModelProvider(this)[NearestViewModel::class.java]
        _binding = FragmentNearestBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.nearestRv.setHasFixedSize(true)
        binding.nearestRv.adapter = listAdapter

        listAdapter.itemListener = this
        listAdapter.stateNameListener = this
        listAdapter.cityNameListener = this


        nearestViewModel.statusProgressBar.observe(viewLifecycleOwner, {
            initProgressbar(it)
        })

        nearestViewModel.getAllData(requireActivity())
        nearestViewModel.fetchAllData.observe(viewLifecycleOwner, {
            listAdapter.setData(it)
        })

        nearestViewModel.getUser(requireActivity())
        nearestViewModel.fetchUser.observe(viewLifecycleOwner, {
            listAdapter.setUser(it)
        })

    }

    fun initProgressbar(state: Boolean) {
        if (state) binding.progressView.visibility = VISIBLE
        else binding.progressView.visibility = GONE

    }

    override fun onItemClicked(ride: Ride) {
        Toast.makeText(requireActivity(), ride.city, Toast.LENGTH_SHORT).show()
    }

    override fun onCityClicked(id: Int) {
        Toast.makeText(requireActivity(), "this is a id $id", Toast.LENGTH_SHORT).show()
    }

    override fun onStateClicked(id: Int) {
        Toast.makeText(requireActivity(), "loading", Toast.LENGTH_SHORT).show()
    }
}