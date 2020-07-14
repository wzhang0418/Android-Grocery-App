package com.apolis.groceryapplication1.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.apolis.groceryapplication1.fragments.SubCategoryFragment
import com.apolis.groceryapplication1.models.SubCategory

class AdapterFragments(fm: FragmentManager): FragmentPagerAdapter(fm) {

    var mFragmentList: ArrayList<Fragment> = ArrayList()
    var mTitleList: ArrayList<String> = ArrayList()

    override fun getItem(position: Int): Fragment {
        return mFragmentList[position]
    }

    override fun getCount(): Int {
        return mTitleList.size
    }

    fun addFragment(list: ArrayList<SubCategory>){
        if(!list.isNullOrEmpty()){
            for(subcategoryData in list){
                mFragmentList.add(SubCategoryFragment.newInstance(subcategoryData.subId))
                mTitleList.add(subcategoryData.subName)
            }
        }

    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mTitleList[position]
    }
}