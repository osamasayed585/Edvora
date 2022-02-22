package com.edvora.edvora.ui.upcoming

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.edvora.edvora.databinding.FragmentUpcomingBinding
import com.edvora.edvora.pojo.Ride
import com.edvora.edvora.ui.adapter.GenerallyAdapter
import com.edvora.edvora.ui.nearst.NearestViewModel


class UpcomingFragment : Fragment(),
    GenerallyAdapter.OnClickGenerallyItemListener,
    GenerallyAdapter.OnClickStateNameListener,
    GenerallyAdapter.OnClickCityNameListener {

    private var _binding: FragmentUpcomingBinding? = null
    private val binding get() = _binding!!
    private val listAdapter: GenerallyAdapter by lazy { GenerallyAdapter() }
    private lateinit var upcomingViewModel: UpcomingViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        upcomingViewModel = ViewModelProvider(this)[UpcomingViewModel::class.java]
        _binding = FragmentUpcomingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.upcomingRv.setHasFixedSize(true)
        binding.upcomingRv.adapter = listAdapter

        listAdapter.itemListener = this
        listAdapter.stateNameListener = this
        listAdapter.cityNameListener = this


        upcomingViewModel.statusProgressBar.observe(viewLifecycleOwner, {
            initProgressbar(it)
        })

        upcomingViewModel.getAllData(requireActivity())
        upcomingViewModel.fetchAllData.observe(viewLifecycleOwner, {
            listAdapter.setData(it)
        })

        upcomingViewModel.getUser(requireActivity())
        upcomingViewModel.fetchUser.observe(viewLifecycleOwner, {
            listAdapter.setUser(it)
        })

    }

    fun initProgressbar(state: Boolean) {
        if (state) binding.progressView.visibility = View.VISIBLE
        else binding.progressView.visibility = View.GONE

    }

    override fun onItemClicked(ride: Ride) {
        TODO("Not yet implemented")
    }

    override fun onCityClicked(id: Int) {
        TODO("Not yet implemented")
    }

    override fun onStateClicked(id: Int) {
        TODO("Not yet implemented")
    }

}