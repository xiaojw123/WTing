package com.ml.lib_base.view.adapter

import android.content.Context
import android.util.Log
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
abstract class BaseRecyclerAdapter<T, VH : BaseRecyclerHolder<*>>() :
    RecyclerView.Adapter<VH>() {


    var mItems: List<T>? = null

    lateinit var mContext: Context


    constructor(items: List<T>) : this() {

        mItems = items
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {


        val itemView = getItemView(parent)
        val clss = javaClass.genericSuperclass as ParameterizedType
        val type = clss.actualTypeArguments[1] as Class<VH>
        val cons = type.kotlin.constructors
        cons.forEach {


            Log.d("bradapter","param__"+it.parameters.size)



//            if (it.parameters.size == 1) {
                APPLOG.printDebug("contruct call"+it)
                return it.call(null,itemView)
//            }

        }


        APPLOG.printDebug("contruct default")
        return BaseRecyclerHolder<ViewDataBinding>(itemView) as VH


    }


    fun getItemView(parent: ViewGroup): View {

        mContext = parent.context
        return CommonUtil.inflater(mContext, getItemLayoutRes(), parent)

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
            bindItemView(holder, position, mItems!!.get(position))

        }
    }


    abstract fun bindItemView(holder: VH, postion: Int, item: T);


}