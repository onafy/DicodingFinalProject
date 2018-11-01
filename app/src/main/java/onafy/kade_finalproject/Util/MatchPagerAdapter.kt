package onafy.kade_finalproject.Util

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import onafy.kade_finalproject.Features.Match.MatchListFragment

class MatchPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        val bundle = Bundle()
        bundle.clear()
        return when (position) {
            0 -> {
                bundle.putString("matchType", "Next Event")
                var fragMatch = MatchListFragment()
                fragMatch.arguments = bundle
                return fragMatch
            }
            else -> {
                bundle.putString("matchType", "Past Event")
                var fragMatch = MatchListFragment()
                fragMatch.arguments = bundle
                return fragMatch

            }
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> "Next Match"
            else -> {
                return "Past Match"
            }
        }
    }


}