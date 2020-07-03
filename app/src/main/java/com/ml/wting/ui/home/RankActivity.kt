package com.ml.wting.ui.home

import android.widget.Toast
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.ml.lib_base.util.CommonUtil
import com.ml.wting.R
import com.ml.wting.repository.model.MVEntity
import com.ml.wting.repository.viewmodel.RankViewModel
import com.ml.wting.ui.base.BaseActivity
import com.ml.wting.util.Constant
import com.ml.wting.util.Constant.PAGE_TYPE
import com.ml.wting.util.Constant.TYPE_DEFAULT
import com.ml.wting.util.Constant.TYPE_MV
import com.ml.wting.view.adapter.RankAdapter

import kotlinx.android.synthetic.main.activity_rank.*

class RankActivity : BaseActivity<ViewDataBinding, RankViewModel>() {


    override fun initView() {


        val type = intent.getIntExtra(PAGE_TYPE, TYPE_DEFAULT)
        if (type == TYPE_DEFAULT) {
            CommonUtil.toast(this, "未知页面类型")
            return
        }


        rank_rlv.layoutManager = LinearLayoutManager(this)


        if (type == TYPE_MV) {


            mViewModel.getRanMV().observe(this, Observer {


                rank_rlv.adapter = RankAdapter(RankAdapter.TYPE_MV, it)


            })

        }



    }

    override fun getLayoutRes(): Int {
        return R.layout.activity_rank
    }


}
