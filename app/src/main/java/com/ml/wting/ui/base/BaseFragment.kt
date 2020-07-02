package com.ml.wting.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.ml.wting.repository.viewmodel.HomeViewModel
import java.lang.reflect.ParameterizedType

@Suppress("NAME_SHADOWING")
abstract class BaseFragment<T : ViewDataBinding, VM : BaseViewModel> : Fragment() {


    lateinit var mBinding: T

    lateinit var mViewModel: VM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        initVM()

        mBinding = DataBindingUtil.inflate<T>(
            LayoutInflater.from(context),
            getLayoutResourse(),
            container,
            false
        )
        initView(mBinding.root)
        return mBinding.root;
    }

    private fun initVM() {
        val type = javaClass.genericSuperclass
        val modelType = (type as ParameterizedType).actualTypeArguments[1]
        mViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(activity!!.application)
            .create(modelType as Class<VM>)
    }


    abstract fun initView(contentView: View);

    abstract fun getLayoutResourse(): Int;


}