package com.ml.wting.ui.view.adapter;

import android.util.SparseArray
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.ml.wting.repository.model.Banner

class BannerAdapter(private val banners: List<Banner>) : PagerAdapter() {


    var itemArray: SparseArray<ImageView>

    init {

        itemArray = SparseArray();
    }


    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }


    override fun getCount(): Int {
        return banners.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        var img = itemArray.get(position)
        if (img == null) {
            img = ImageView(container.context)
            img.layoutParams = ViewPager.LayoutParams()
            img.scaleType = ImageView.ScaleType.FIT_XY
            itemArray.put(position, img)
        }

        container.addView(img)
        return img;
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val itemView = itemArray.get(position)
        if (itemView != null) {
            itemArray.remove(position)
            container.removeView(itemView)
        }
    }

}