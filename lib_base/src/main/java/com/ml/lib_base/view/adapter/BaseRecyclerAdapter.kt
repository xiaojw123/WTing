package com.ml.lib_base.view.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.ml.lib_base.util.APPLOG
import com.ml.lib_base.util.CommonUtil
import java.lang.reflect.ParameterizedType
import kotlin.reflect.KClass

/**
 *
 * RecyclerAdapter通用基类
 * 适用于viewType，数据类型单一
 *
 */
abstract class BaseRecyclerAdapter<T, VH : BaseRecyclerHolder, VB : ViewDataBinding>() :
    RecyclerView.Adapter<VH>() {


    var mItems: List<T>? = null

    lateinit var mContext: Context
    lateinit var mBinding: VB


    constructor(items: List<T>) : this() {

        mItems = items
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {


        val itemView = getItemView(parent)


        val clss = javaClass.genericSuperclass as ParameterizedType
        val type = clss.actualTypeArguments[1] as Class<VH>
        val cons = type.kotlin.constructors
        cons.forEach {


            if (it.parameters.size == 1) {
                APPLOG.printDebug("contruct call")
                return it.call(itemView)
            }

        }


        APPLOG.printDebug("contruct default")
        return BaseRecyclerHolder(itemView) as VH


    }


    fun getItemView(parent: ViewGroup): View {
        mContext = parent.context
        mBinding = CommonUtil.bindInflater(mContext, getItemLayoutRes(), parent)
        return mBinding.root
    }

    abstract fun getItemLayoutRes(): Int

    override fun getItemCount(): Int {
        return mItems?.size ?: 0
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        if (mItems == null) {
            return
        }
        if (mItems?.size!! > 0) {
            bindItemView(holder.itemView, position, mItems!!.get(position))

        }
    }


    abstract fun bindItemView(itemView: View, postion: Int, item: T);


}