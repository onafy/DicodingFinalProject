package onafy.kade_finalproject.Features.Match

import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v7.widget.SearchView
import android.util.Log
import android.view.*
import android.widget.FrameLayout
import onafy.kade_finalproject.Features.Search.SearchMatchFragment
import onafy.kade_finalproject.R
import onafy.kade_finalproject.Util.ViewPagerAdapter
import onafy.kade_finalproject.Util.gone
import onafy.kade_finalproject.Util.visible
import org.jetbrains.anko.find


class MatchFragment() : Fragment() {

    private var isDefault: Boolean = true
    private lateinit var mViewPager : ViewPager
    private lateinit var mContainer : FrameLayout
    private lateinit var tabs : TabLayout
    private lateinit var tabWrapper : AppBarLayout
    private lateinit var matchAdapter : ViewPagerAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        mContainer = view.find(R.id.container)
        mViewPager = view.find(R.id.viewpager_main)
        tabWrapper = view.find(R.id.idTabWrapper)
        tabs = view.find(R.id.tabs_main)
        matchAdapter = ViewPagerAdapter(childFragmentManager)

        setDefaultFragment()

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.activity_match, container, false)
    }

    // ---------------------------- additional Function ------------------------------------------------------------------------
    private fun setDefaultFragment() {
        if (isAdded) {
            matchAdapter.populateFragment(loadMatchFragment("Next Event"), "Next")
            matchAdapter.populateFragment(loadMatchFragment(), "Past")

            mViewPager.adapter = matchAdapter
            mViewPager.currentItem = 0

            tabs.setupWithViewPager(mViewPager)

            if (mContainer.visibility == View.VISIBLE) mContainer.gone()
            if (mViewPager.visibility == View.GONE) mViewPager.visible()

            mContainer.removeAllViews()
            tabWrapper.visible()
            isDefault = true

        }
        else if (isDetached){

        }
    }


    private fun setSearch(query: String = "") {
        val bundle = Bundle()
        bundle.putString("query", query)
        val searchFrag = SearchMatchFragment()
        searchFrag.arguments = bundle
        childFragmentManager
                .beginTransaction()
                .replace(R.id.container, searchFrag, SearchMatchFragment::class.java.simpleName)
                .commit()

        if (mViewPager.visibility == View.VISIBLE) mViewPager.gone()
        if (mContainer.visibility == View.GONE) mContainer.visible()

        mViewPager.removeAllViews()
        matchAdapter.removeAllFragment()
        tabWrapper.gone()
        isDefault = false
    }


    private fun loadMatchFragment(matchType: String = "Past Event"): MatchListFragment {
        val bundle = Bundle()
        bundle.putString("matchType", matchType)
        Log.i("sampe disini", "sampemi di loadd")
        Log.d("isi bundle", bundle.getString("matchType"))
        var fragMatch = MatchListFragment()
        fragMatch.arguments = bundle
        return fragMatch
    }


    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.search_menu, menu)
        val searchBar = menu?.findItem(R.id.searchBar)?.actionView as SearchView?
        val search = menu?.findItem(R.id.searchBar)

        search?.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
                return true
            }

            override fun onMenuItemActionCollapse(item: MenuItem): Boolean {
                val currentFrag = childFragmentManager.findFragmentByTag(SearchMatchFragment::class.java.simpleName)
                if (currentFrag != null && currentFrag.isVisible) {
                    setDefaultFragment()
                }
                return true
            }
        })
        searchBar?.queryHint = getString(R.string.match_search)
        searchBar?.setOnQueryTextListener(object : android.support.v7.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                setSearch(query)
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                if (newText.isEmpty() && !isDefault) {
                    setDefaultFragment()
                }
                return false
            }
        })
    }

    override fun onPrepareOptionsMenu(menu: Menu?) {
        super.onPrepareOptionsMenu(menu)
        val search = menu?.findItem(R.id.searchBar)
        search?.collapseActionView()
    }

 //-----------------------------------------------------------------------------------------------------------------------





}






