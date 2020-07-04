package com.ml.wting.view.adapter

import android.content.Context
import android.os.Build
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ml.lib_base.util.APPLOG
import com.ml.lib_base.util.CommonUtil
import com.ml.lib_base.util.DXUtil
import com.ml.lib_base.util.DrawableUtil
import com.ml.wting.R
import com.ml.wting.repository.model.ArtistItem
import com.ml.wting.repository.model.MVEntity
import com.ml.wting.repository.model.SongListItem
import com.ml.wting.util.Constant
import io.reactivex.internal.fuseable.HasUpstreamObservableSource

import kotlinx.android.synthetic.main.list_item_category.*
import kotlinx.android.synthetic.main.list_item_songer.*

class RankAdapter<T>() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    var mItems: List<T>? = null
    var mContext: Context? = null

    var mType = -1


    constructor(viewType: Int, items: List<T>) : this() {

        mItems = items
        mType = viewType
    }

    //mv 歌单、榜单通用

    inner class CommonHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var authorTv: TextView
        var titleTv: TextView
        var img: ImageView

        init {
            authorTv = itemView.findViewById(R.id.item_category_author)
            titleTv = itemView.findViewById(R.id.item_category_title)
            img = itemView.findViewById(R.id.item_category_img)
        }

    }

    inner class HeadHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


    }

    inner class SongerHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        var rankTv: TextView
        var nameTv: TextView
        var avatarImg: ImageView
        var hotTv: TextView


        init {

            rankTv = itemView.findViewById(R.id.item_songer_num)
            nameTv = itemView.findViewById(R.id.item_songer_name)
            avatarImg = itemView.findViewById(R.id.item_songer_img)
            hotTv = itemView.findViewById(R.id.item_songer_hot)


        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        mContext = parent.context
        when (mType) {


            Constant.TYPE_SONGER -> {
                return SongerHolder(
                    CommonUtil.inflater(
                        mContext,
                        R.layout.list_item_songer,
                        parent
                    )
                )
            }

            else -> {
                if (mType == Constant.TYPE_MV) {
                    if (viewType == 0) {
                        return HeadHolder(getHeadTilteView("MV"))
                    }
                }
                return CommonHolder(
                    CommonUtil.inflater(
                        mContext,
                        R.layout.list_item_category,
                        parent
                    )
                )


            }


        }

    }


    fun getHeadTilteView(title: String): TextView {
        val title_tv = TextView(mContext)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            title_tv.setTextAppearance(R.style.text_bold_style)
        }
        title_tv.setTextSize(32f)
        title_tv.setPadding(0, DXUtil.dip2px(mContext!!, 20f), 0, 0)
        title_tv.setText(title)
        return title_tv

    }


    override fun getItemViewType(position: Int): Int {
        return position
    }


    override fun getItemCount(): Int {


        return mItems?.size ?: 0
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = mItems?.get(position)

        if (holder is RankAdapter<*>.CommonHolder) {
            var title: String? = null
            var author: String? = null
            var imgUrl: String? = null
            when (mType) {
                Constant.TYPE_MV -> {

                    if (item is MVEntity) {
                        APPLOG.printDebug("Entify__")
                        title = item.name
                        author = item.artistName
                        imgUrl = item.cover
                    }

                }
                Constant.TYPE_SONGLIST -> {

                    if (item is SongListItem) {
                        title=item.name
                        author=item.creator.nickname
                        imgUrl=item.coverImgUrl

                    }


                }


            }
            holder.titleTv.setText(title)
            holder.authorTv.setText(author)
            DrawableUtil.load(mContext, imgUrl, holder.img)

        }
        if (holder is RankAdapter<*>.SongerHolder) {


            if (item is ArtistItem) {

                holder.nameTv.setText(item.name)
                holder.hotTv.setText(item.score.toString())
                holder.rankTv.setText((position+1).toString())
                DrawableUtil.loadRound(mContext, item.picUrl,10f, holder.avatarImg)


            }


        }


    }

}