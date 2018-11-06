package onafy.kade_finalproject.Util

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import onafy.kade_finalproject.Features.TeamDetail.Player.PlayerFragment
import onafy.kade_finalproject.Features.TeamDetail.TeamDescFragment

class DetailTeamPagerAdapter(fm: FragmentManager, private val teamId: String, private val teamDesc: String) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        val bundle = Bundle()
        bundle.clear()
        return when (position) {
            0 -> {
                bundle.putString("detailTeam", teamDesc)
                var fragMatch = TeamDescFragment()
                fragMatch.arguments = bundle
                return fragMatch
            }
            else -> {
                bundle.putString("idTeam", teamId)
                var fragMatch = PlayerFragment()
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
            0 -> "Description"
            else -> {
                return "Player"
            }
        }
    }


}