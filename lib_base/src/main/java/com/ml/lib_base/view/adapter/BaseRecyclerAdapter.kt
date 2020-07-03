package com.ml.lib_base.view.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ml.lib_base.util.CommonUtil
import java.lang.reflect.ParameterizedType

/**
 *
 * RecyclerAdapter通用基类
 * 适用于viewType，数据类型单一
 *
 */
abstract class BaseRecyclerAdapter<T, VH : BaseRecyclerHolder>() :
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

        val constructor = type.getDeclaredConstructor(

            View::class.java
        )
        constructor.isAccessible = true
        return constructor.newInstance(itemView)


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
        bindItemView(holder.itemView, position)
    }


    abstract fun bindItemView(itemView: View, position: Int);


}