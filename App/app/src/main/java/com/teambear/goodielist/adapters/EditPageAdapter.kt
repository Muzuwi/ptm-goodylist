package com.teambear.goodielist.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.teambear.goodielist.FragmentRecipeEditIngredients
import com.teambear.goodielist.FragmentRecipeEditMain
import com.teambear.goodielist.FragmentRecipeEditSteps

class EditPageAdapter(
    val fm: FragmentManager
) : FragmentPagerAdapter(fm) {

    init {
        println("Adapter is recreated")
    }

    val pageFragments: List<Fragment> = listOf(
        FragmentRecipeEditMain(),
        FragmentRecipeEditIngredients(),
        FragmentRecipeEditSteps(),
    )

    override fun getCount(): Int = pageFragments.size

    override fun getItem(i: Int): Fragment {
        return pageFragments[i]
    }

    override fun getPageTitle(position: Int): CharSequence {
        return "OBJECT ${(position + 1)}"
    }
}
