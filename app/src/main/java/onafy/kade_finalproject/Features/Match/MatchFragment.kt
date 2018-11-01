package onafy.kade_finalproject.Features.Match

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.*
import android.widget.FrameLayout
import kotlinx.android.synthetic.main.activity_match.*
import onafy.kade_finalproject.Util.MatchPagerAdapter
import onafy.kade_finalproject.R
import org.jetbrains.anko.*


class MatchFragment() : Fragment() {

    private var isDefault: Boolean = true
    private lateinit var mViewPager : ViewPager
    private lateinit var mContainer : FrameLayout
    private lateinit var tabs : TabLayout
    private lateinit var matchAdapter : MatchPagerAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        mContainer = view.find(R.id.container)
        mViewPager = view.find(R.id.viewpager_main)
        tabs = view.find(R.id.tabs_main)

        matchAdapter = MatchPagerAdapter(childFragmentManager)
        viewpager_main.adapter = matchAdapter
        tabs_main.setupWithViewPager(mViewPager)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.activity_match, container, false)
    }


}






