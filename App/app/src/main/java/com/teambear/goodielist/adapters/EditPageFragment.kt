package com.teambear.goodielist.adapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.teambear.goodielist.R

class EditPageFragment : Fragment() {
    // When requested, this adapter returns a DemoObjectFragment,
    // representing an object in the collection.
    private lateinit var editPageAdapter: EditPageAdapter
    private lateinit var viewPager: ViewPager

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_recipe_edit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        editPageAdapter = EditPageAdapter(childFragmentManager)
        viewPager = view.findViewById(R.id.EditViewPager)
        viewPager.adapter = editPageAdapter

        val tabLayout = view.findViewById<TabLayout>(R.id.editTabLayout)
        tabLayout.setupWithViewPager(viewPager)
    }
}

// Since this is an object collection, use a FragmentStatePagerAdapter,
// and NOT a FragmentPagerAdapter.
class EditPageAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    val pageList: List<Fragment> = listOf(
        fm.findFragmentById(R.id.EditViewMain)!!,
        fm.findFragmentById(R.id.EditViewIngredients)!!,
        fm.findFragmentById(R.id.EditViewSteps)!!
    )

    override fun getCount(): Int = pageList.size

    override fun getItem(i: Int): Fragment {
        return pageList[i]
    }

    override fun getPageTitle(position: Int): CharSequence {
        return "OBJECT ${(position + 1)}"
    }
}

private const val ARG_OBJECT = "object"