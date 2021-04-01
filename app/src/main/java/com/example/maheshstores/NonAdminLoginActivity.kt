package com.example.maheshstores

import android.os.Bundle
import android.util.Log
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayout

class NonAdminLoginActivity : AppCompatActivity() {
    lateinit var pager: FrameLayout
    lateinit var textview:TextView
    lateinit var tab:TabLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_non_admin_login)
        pager = findViewById(R.id.pager)
        textview = findViewById(R.id.text)
        tab = findViewById(R.id.tab)

        tab.addOnTabSelectedListener(object:TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when(tab?.position)
                {
                    0->{goto_roll_number()}
                    else->{goto_components()}
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })

    }

    fun goto_components()
    {
        Log.d("timepass","Inside components")
        textview.text = "Inside components list"
    }

    fun goto_roll_number()
    {
        Log.d("timepass","Inside roll")
        textview.text = "Inside Roll NO. search engine"
    }
}