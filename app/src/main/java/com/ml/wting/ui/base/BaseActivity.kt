package com.ml.wting.ui.base

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.reflect.ParameterizedType

abstract class BaseActivity<T : ViewDataBinding, VM : ViewModel> : AppCompatActivity() {

    lateinit var mBindng: T
    lateinit var mViewModel: VM
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val type = javaClass.genericSuperclass
        if (type is ParameterizedType) {
            val paramType1 = type.actualTypeArguments[0]
            val paramType2 = type.actualTypeArguments[1]
            if (paramType1 is ViewDataBinding) {
                setContentView(getLayoutRes())
            } else {
                mBindng = DataBindingUtil.setContentView(this, getLayoutRes())
            }
            if (paramType2 !is ViewModel) {
                mViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(application)
                    .create(paramType2 as Class<VM>)
            }
        }

        initView()
    }

    abstract fun initView();
    abstract fun getLayoutRes(): Int
}
