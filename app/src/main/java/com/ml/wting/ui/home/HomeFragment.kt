package com.ml.wting.ui.home

import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ml.wting.R
import com.ml.wting.databinding.FragmentHomeBinding
import com.ml.wting.repository.viewmodel.HomeViewModel
import com.ml.wting.ui.base.BaseFragment
import com.ml.wting.ui.view.adapter.BannerAdapter

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    override fun initView(contentView: View) {

        val vm = ViewModelProvider.AndroidViewModelFactory.getInstance(activity!!.application)
            .create(HomeViewModel::class.java)

        vm.getBanner().observe(this, Observer { value ->
            if (value != null && value.size > 0) {
                mBinding.homeVp.adapter = BannerAdapter(value)
            }
        })


    }

    override fun getLayoutResourse(): Int {
        return R.layout.fragment_home
    }


}