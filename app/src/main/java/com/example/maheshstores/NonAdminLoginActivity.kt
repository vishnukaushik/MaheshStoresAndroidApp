package com.example.maheshstores

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

class NonAdminLoginActivity : AppCompatActivity() {
    lateinit var pager: ViewPager
    lateinit var tab: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_non_admin_login)
        pager = findViewById(R.id.view_pager)
        pager.adapter = CustomPagerAdapter(supportFragmentManager,this)

        tab = findViewById(R.id.tab)
        tab.setupWithViewPager(pager)

    }

    private class CustomPagerAdapter(fm:FragmentManager, val context: Context):FragmentPagerAdapter(fm,
        BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)
    {
        override fun getCount(): Int {
            return 2
        }

        override fun getItem(position: Int): Fragment {
            return when(position)
            {
                0->RollSearchFragment()
                else->ComponentSearchFragment()
            }
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return when(position)
            {
                0->context.resources.getString(R.string.search_by_roll_number)
                else->context.resources.getString(R.string.search_by_component)
            }
        }
    }

    override fun onBackPressed() {
        // if there is a fragment and the back stack of this fragment is not empty,
        // then emulate 'onBackPressed' behaviour, because in default, it is not working
        val fm: FragmentManager = supportFragmentManager
        val child = supportFragmentManager
        for (frag in fm.fragments) {
            if (frag.isVisible) {
                val childFm: FragmentManager = frag.childFragmentManager
                if (childFm.backStackEntryCount > 0) {
                    childFm.popBackStack()
                    return
                }
            }
        }
        super.onBackPressed()
    }
}