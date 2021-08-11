package com.teambear.goodielist.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.teambear.goodielist.FragmentRecipeEditIngredients
import com.teambear.goodielist.FragmentRecipeEditMain
import com.teambear.goodielist.FragmentRecipeEditSteps
import com.teambear.goodielist.interfaces.IRecipeEditViewCreated

class EditPageAdapter(
    fm: FragmentManager,
    viewCreatedListener: IRecipeEditViewCreated
) : FragmentPagerAdapter(fm) {

    init {
        println("Adapter is recreated")
    }

    val pageFragments: List<Fragment> = listOf(
        FragmentRecipeEditMain(viewCreatedListener),
        FragmentRecipeEditIngredients(viewCreatedListener),
        FragmentRecipeEditSteps(viewCreatedListener),
    )

    val pageLabels: List<String> = listOf(
        "Main",
        "Ingredients",
        "Steps"
        )

    override fun getCount(): Int = pageFragments.size

    override fun getItem(i: Int): Fragment {
        return pageFragments[i]
    }

    override fun getPageTitle(position: Int): CharSequence {
        return pageLabels[position]
    }
}
