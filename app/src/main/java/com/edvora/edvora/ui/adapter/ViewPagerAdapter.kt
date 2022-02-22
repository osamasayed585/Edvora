package com.edvora.edvora.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.edvora.edvora.ui.Past.PastFragment
import com.edvora.edvora.ui.nearst.NearestFragment
import com.edvora.edvora.ui.upcoming.UpcomingFragment

private const val NUM_TABS = 3

class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return NUM_TABS
    }

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return NearestFragment()
            1 -> return UpcomingFragment()
        }
        return PastFragment()
    }

}