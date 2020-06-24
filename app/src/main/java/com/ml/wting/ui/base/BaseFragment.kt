package com.ml.wting.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

@Suppress("NAME_SHADOWING")
abstract class BaseFragment<T : ViewDataBinding> : Fragment() {


    lateinit var mBinding: T

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate<T>(
            LayoutInflater.from(context),
            getLayoutResourse(),
            container,
            false
        )
        initView(mBinding.root)
        return mBinding.root;
    }


    abstract fun initView(contentView: View);

    abstract fun getLayoutResourse(): Int;


}