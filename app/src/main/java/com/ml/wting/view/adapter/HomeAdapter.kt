package com.ml.wting.view.adapter;

import android.content.Context
import com.ml.wting.repository.model.BannerItem
import com.youth.banner.listener.OnBannerListener
import com.ml.wting.view.adapter.HomeBannerAdapter
import com.ml.wting.view.adapter.HomeCategoryAdapter
import com.ml.lib_base.util.CommonUtil
import com.youth.banner.Banner
import com.youth.banner.indicator.CircleIndicator
import androidx.recyclerview.widget.RecyclerView
import com.ml.lib_base.util.APPLOG
import com.ml.lib_base.util.DXUtil
import com.ml.lib_base.view.decoration.SpaceDecoration
import com.ml.wting.repository.model.CategoryItem
import android.util.SparseArray
import android.view.View

import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner

import androidx.recyclerview.widget.GridLayoutManager
import com.ml.wting.R
import com.ml.wting.ui.home.HomeFragment.Companion.TYPE_BANNER
import com.ml.wting.ui.home.HomeFragment.Companion.TYPE_M_RECOMMEND
import com.ml.wting.ui.home.HomeFragment.Companion.TYPE_RANK
import com.ml.wting.ui.home.HomeFragment.Companion.TYPE_S_NEW
import com.ml.wting.ui.home.RankActivity
import com.ml.wting.util.Constant

class HomeAdapter(context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>(),
    OnBannerListener<BannerItem> {

    var context: Context

    var bannerHeight: Int

    var mBanners: List<BannerItem>? = null

    var mCategoryArray: SparseArray<List<CategoryItem>>


    init {
        this.context = context
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
                banner.addBannerLifecycleObserver(context as LifecycleOwner)
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
                holder.categoryRLV.adapter = HomeCategoryAdapter(position,categoryItems)
            }
        }


    }

    override fun OnBannerClick(data: BannerItem?, position: Int) {


    }


    inner class BannerHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    inner class RankHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {


        var rankMV: ViewGroup
        var rankSonger: ViewGroup
        var rankSongList:ViewGroup
        var ranlist:ViewGroup

        init {
            rankMV = itemView.findViewById(R.id.home_rank_mv)
            rankSonger = itemView.findViewById(R.id.home_rank_songer)
            rankSongList=itemView.findViewById(R.id.home_rank_songlist)
            ranlist=itemView.findViewById(R.id.home_rank_list)
            rankMV.setOnClickListener(this)
            rankSonger.setOnClickListener(this)
            rankSongList.setOnClickListener(this)
            ranlist.setOnClickListener(this)

        }

        override fun onClick(v: View?) {
            var type = Constant.TYPE_DEFAULT
            when (v?.id) {
                R.id.home_rank_mv -> type = Constant.TYPE_MV
                R.id.home_rank_songer -> type = Constant.TYPE_SONGER
                R.id.home_rank_songlist -> type = Constant.TYPE_SONGLIST
                R.id.home_rank_list -> type = Constant.TYPE_RANKLIST


            }
            CommonUtil.sGotoPage(
                context, RankActivity::class.java,
                Constant.PAGE_TYPE, type
            )
        }


    }

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
            categoryRLV.addItemDecoration(SpaceDecoration(5, context!!))


        }



    }
}