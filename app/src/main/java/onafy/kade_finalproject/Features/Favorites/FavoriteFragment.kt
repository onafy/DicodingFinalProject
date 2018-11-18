package onafy.kade_finalproject.Features.Favorites


import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import onafy.kade_finalproject.R
import onafy.kade_finalproject.Util.ViewPagerAdapter
import org.jetbrains.anko.find

class FavoriteFragment : Fragment() {
    private lateinit var mViewPager : ViewPager
    private lateinit var mContainer : FrameLayout
    private lateinit var tabs : TabLayout
    private lateinit var tabWrapper : AppBarLayout
    private lateinit var favAdapter : ViewPagerAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mContainer = view.find(R.id.containers)
        mViewPager = view.find(R.id.viewpager_mains)
        tabWrapper = view.find(R.id.idTabWrappers)
        tabs = view.find(R.id.tabs_mains)

        favAdapter = ViewPagerAdapter(childFragmentManager)

        favAdapter.populateFragment(FavoriteMatchFragment(), "Match")
        favAdapter.populateFragment(FavoriteTeamsFragment(), "Team")

        mViewPager.adapter = favAdapter
        mViewPager.currentItem = 0
        tabs.setupWithViewPager(mViewPager)

    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }




}
