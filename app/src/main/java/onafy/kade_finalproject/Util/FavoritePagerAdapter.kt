package onafy.kade_finalproject.Util

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import onafy.kade_finalproject.Features.Favorites.FavoriteMatchFragment
import onafy.kade_finalproject.Features.Favorites.FavoriteTeamsFragment

class FavoritePagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        val bundle = Bundle()
        bundle.clear()
        return when (position) {
            0 -> {
                bundle.putString("favoriteType", "Match")
                var fragMatchFav = FavoriteMatchFragment()
                fragMatchFav.arguments = bundle
                return fragMatchFav
            }
            else -> {
                bundle.putString("favoriteType", "Team")
                var fragTeamFav = FavoriteTeamsFragment()
                fragTeamFav.arguments = bundle
                return fragTeamFav

            }
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> "Match"
            else -> {
                return "Team"
            }
        }
    }


}