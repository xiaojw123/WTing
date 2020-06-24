package com.ml.wting.ui.base

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<T : ViewDataBinding> : AppCompatActivity() {

    lateinit var mBindng: T
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBindng = DataBindingUtil.setContentView(this, getLayoutRes())
        initView();
    }

    abstract fun initView();
    abstract fun getLayoutRes(): Int
}
