package com.ml.wting.ui.home

import androidx.recyclerview.widget.LinearLayoutManager
import android.view.View
import com.ml.wting.R
import androidx.lifecycle.Observer
import com.ml.wting.databinding.FragmentHomeBinding

import com.ml.wting.repository.viewmodel.HomeViewModel
import com.ml.wting.ui.base.BaseFragment
import com.ml.wting.view.adapter.HomeAdapter

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {
    companion object {

        const val TYPE_BANNER = 0;
        const val TYPE_RANK = 1
        const val TYPE_S_NEW = 2;//最新音乐
        const val TYPE_M_RECOMMEND = 3;//推荐
    }


    override fun initView(contentView: View) {

        mBinding.homeRlv.layoutManager = LinearLayoutManager(context)
        val homeAdapter = HomeAdapter(context!!)
        mBinding.homeRlv.adapter = homeAdapter

        mViewModel.getBanner().observe(this,
            Observer {

                    value ->

                if (value?.size!! > 0) {
                    homeAdapter.updateBanner(value)
                }


            })
        mViewModel.getNewSong().observe(this, Observer {


                value ->
            if (value?.size!! > 0) {

                homeAdapter.updateCategory(TYPE_S_NEW, value)
            }


        })
        mViewModel.getRecommnedMV().observe(this, Observer {

                value ->
            if (value?.size!! > 0) {

                homeAdapter.updateCategory(TYPE_M_RECOMMEND, value)
            }


        })


    }





    override fun getLayoutResourse(): Int {
        return R.layout.fragment_home
    }


}