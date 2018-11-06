package onafy.kade_finalproject.Features.Favorites


import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import kotlinx.android.synthetic.main.activity_match.*
import onafy.kade_finalproject.R
import onafy.kade_finalproject.Util.FavoritePagerAdapter
import org.jetbrains.anko.find

class FavoriteFragment : Fragment() {
    private lateinit var mViewPager : ViewPager
    private lateinit var mContainer : FrameLayout
    private lateinit var tabs : TabLayout
    private lateinit var favAdapter : FavoritePagerAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        mContainer = view.find(R.id.container)
        mViewPager = view.find(R.id.viewpager_main)
        tabs = view.find(R.id.tabs_main)

        favAdapter = FavoritePagerAdapter(childFragmentManager)
        viewpager_main.adapter = favAdapter
        tabs_main.setupWithViewPager(mViewPager)

    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }


}
