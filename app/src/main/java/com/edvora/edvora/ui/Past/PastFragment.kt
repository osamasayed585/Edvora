package com.edvora.edvora.ui.Past

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.edvora.edvora.R
import com.edvora.edvora.databinding.FragmentNearestBinding
import com.edvora.edvora.databinding.FragmentPastBinding
import com.edvora.edvora.pojo.Ride
import com.edvora.edvora.ui.adapter.GenerallyAdapter
import com.edvora.edvora.ui.upcoming.UpcomingViewModel


class PastFragment : Fragment(),
    GenerallyAdapter.OnClickGenerallyItemListener,
    GenerallyAdapter.OnClickStateNameListener,
    GenerallyAdapter.OnClickCityNameListener {

    private var _binding: FragmentPastBinding? = null
    private val binding get() = _binding!!
    private val listAdapter: GenerallyAdapter by lazy { GenerallyAdapter() }
    private lateinit var pastViewModel: PastViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        pastViewModel = ViewModelProvider(this)[PastViewModel::class.java]
        _binding = FragmentPastBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.pastRv.setHasFixedSize(true)
        binding.pastRv.adapter = listAdapter

        listAdapter.itemListener = this
        listAdapter.stateNameListener = this
        listAdapter.cityNameListener = this


        pastViewModel.statusProgressBar.observe(viewLifecycleOwner, {
            initProgressbar(it)
        })

        pastViewModel.getAllData(requireActivity())
        pastViewModel.fetchAllData.observe(viewLifecycleOwner, {
            listAdapter.setData(it)
        })

        pastViewModel.getUser(requireActivity())
        pastViewModel.fetchUser.observe(viewLifecycleOwner, {
            listAdapter.setUser(it)
        })

    }

    fun initProgressbar(state: Boolean) {
        if (state) binding.progressView.visibility = View.VISIBLE
        else binding.progressView.visibility = View.GONE

    }

    override fun onItemClicked(ride: Ride) {
        // here I can any thing
    }

    override fun onCityClicked(id: Int) {
        // here I can any thing
    }

    override fun onStateClicked(id: Int) {
        // here I can any thing
    }

}