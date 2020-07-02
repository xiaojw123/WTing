package com.ml.wting.ui.home

import android.util.SparseArray
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ml.lib_base.util.APPLOG
import com.ml.lib_base.util.DXUtil
import com.ml.lib_base.view.decoration.SpaceDecoration
import com.ml.wting.R
import com.ml.wting.databinding.FragmentHomeBinding
import com.ml.wting.repository.model.BannerItem
import com.ml.wting.repository.model.CategoryItem
import com.ml.wting.repository.viewmodel.HomeViewModel
import com.ml.wting.ui.base.BaseFragment
import com.ml.wting.ui.view.adapter.HomeBannerAdapter
import com.ml.wting.ui.view.adapter.HomeCategoryAdapter
import com.ml.wting.util.CommonUtil
import com.youth.banner.Banner
import com.youth.banner.indicator.CircleIndicator
import com.youth.banner.listener.OnBannerListener

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {
    companion object {

        const val TYPE_BANNER = 0;
        const val TYPE_RANK = 1
        const val TYPE_S_NEW = 2;//最新音乐
        const val TYPE_M_RECOMMEND = 3;//推荐
    }


    override fun initView(contentView: View) {

        mBinding.homeRlv.layoutManager = LinearLayoutManager(context)
        val homeAdapter = HomeAdapter()
        mBinding.homeRlv.adapter = homeAdapter

        mViewModel.getBanner().observe(this@HomeFragment,
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


    inner class HomeAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(),
        OnBannerListener<BannerItem> {


        var bannerHeight: Int

        var mBanners: List<BannerItem>? = null

        var mCategoryArray: SparseArray<List<CategoryItem>>


        init {
            bannerHeight = DXUtil.dip2px(context!!, 200f)

            mCategoryArray = SparseArray()

        }

        fun updateBanner(banners: List<BannerItem>) {
            APPLOG.printDebug("updateBanner___")
            mBanners = banners;
            notifyItemRangeChanged(0, 1)
        }

        fun updateCategory(type: Int, categoryItems: List<CategoryItem>) {

            mCategoryArray.put(type, categoryItems)
            notifyItemRangeChanged(type, 1)


        }


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

            when (viewType) {
                TYPE_BANNER -> {
                    return BannerHolder(getBanner())
                }
                TYPE_RANK -> {


                    return RankHolder(CommonUtil.inflater(context, R.layout.home_item_rank, parent))

                }

                else -> return CategoryHolder(
                    viewType,
                    CommonUtil.inflater(
                        context,
                        R.layout.home_item_category,
                        parent
                    )
                )


            }


        }

        private fun getBanner(): Banner<BannerItem, HomeBannerAdapter> {
            val banner = Banner<BannerItem, HomeBannerAdapter>(context);

            val param = RecyclerView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                bannerHeight
            )
            banner.layoutParams = param
            banner.setOnBannerListener(this)
            return banner
        }

        override fun getItemCount(): Int {
            return 4
        }

        override fun getItemViewType(position: Int): Int {
            return position
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

            APPLOG.printDebug("bindVH__" + holder + ", positoin__" + position)
            if (holder is BannerHolder) {
                if (mBanners != null) {
                    val banner = holder.itemView as Banner<*, *>
                    banner.addBannerLifecycleObserver(this@HomeFragment)
                    banner.adapter = HomeBannerAdapter(mBanners)
                    banner.indicator = CircleIndicator(context)
                    banner.start()
                }
            } else if (holder is CategoryHolder) {

                if (mCategoryArray.size() > 0) {


                    var titleName = ""
                    when (position) {
                        TYPE_S_NEW -> titleName = "最新音乐 >"
                        TYPE_M_RECOMMEND -> titleName = "推荐MV >"
                    }
                    holder.categoryTv.setText(titleName)
                    val categoryItems = mCategoryArray[position]
                    holder.categoryRLV.adapter = HomeCategoryAdapter(categoryItems)
                }
            }


        }

        override fun OnBannerClick(data: BannerItem?, position: Int) {


        }


    }


    inner class BannerHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    inner class RankHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    inner class CategoryHolder(type: Int, itemView: View) : RecyclerView.ViewHolder(itemView) {


        var categoryTv: TextView
        var categoryRLV: RecyclerView

        init {
            categoryTv = itemView.findViewById(R.id.home_category_tv)
            categoryRLV = itemView.findViewById(R.id.home_category_rlv)
            val gm = object : GridLayoutManager(context, if (type == TYPE_M_RECOMMEND) 2 else 3) {
                override fun canScrollVertically(): Boolean {
                    return false
                }
            }
            categoryRLV.layoutManager = gm
            categoryRLV.addItemDecoration(SpaceDecoration(5,context!!))
        }

    }


    override fun getLayoutResourse(): Int {
        return R.layout.fragment_home
    }


}