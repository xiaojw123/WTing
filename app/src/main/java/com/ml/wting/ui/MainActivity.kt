package com.ml.wting.ui

import android.os.Bundle
import android.util.SparseArray
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.ml.wting.R
import com.ml.wting.databinding.ActivityMainBinding
import com.ml.wting.ui.base.BaseActivity
import com.ml.wting.ui.home.HomeFragment

//http://neteasecloudmusicapi.zhaoboy.com/banner
class MainActivity : BaseActivity<ActivityMainBinding,ViewModel>() {


    var mFragmentArray = SparseArray<Fragment>()


    override fun initView() {
        initData()
        switchFramgent(R.id.main_home_tab)
    }


    private fun initData() {
        mFragmentArray.put(R.id.main_home_tab, HomeFragment())
    }


    fun onTabClick(view: View) {
        switchFramgent(view.id)
    }


    fun switchFramgent(tabId: Int) {

        val fm = supportFragmentManager;

        val ft = fm.beginTransaction();

        val framgent = mFragmentArray[tabId]

        for (i in 0 until mFragmentArray.size()) {
            val item = mFragmentArray.valueAt(i)
            if (item != framgent) {
                if (item.isVisible) {
                    ft.hide(item)
                }
            }
        }
        if (framgent.isAdded) {
            ft.show(framgent)
        } else {
            ft.add(R.id.main_fragment_container, framgent)
        }
        ft.commitNow()
    }

    override fun getLayoutRes(): Int {
        return R.layout.activity_main
    }


}
