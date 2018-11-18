package onafy.kade_finalproject.Util

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.util.Log

class ViewPagerAdapter(fragmentManager: FragmentManager?) : FragmentPagerAdapter(fragmentManager){

    var fragmentList = arrayListOf<Fragment>()
    var titleList = arrayListOf<String>()

    fun populateFragment(fragment: Fragment, title: String){
        Log.i("viewpager", "viewpager")
        fragmentList.add(fragment)
        titleList.add(title)
        Log.d("fragmentlist", fragmentList.toString())
    }

    fun removeAllFragment(){
        fragmentList.clear()
        titleList.clear()
    }

    override fun getItem(position: Int) = fragmentList[position]

    override fun getCount() = fragmentList.size

    override fun getPageTitle(position: Int) = titleList[position]
}