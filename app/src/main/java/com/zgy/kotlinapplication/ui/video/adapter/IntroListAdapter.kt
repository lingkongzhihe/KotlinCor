package com.zgy.kotlinapplication.ui.video.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zgy.kotlinapplication.R
import com.zgy.kotlinapplication.databinding.ItemHomeBinding
import com.zgy.kotlinapplication.databinding.ItemIntroVideoBinding
import com.zgy.kotlinapplication.logic.model.Daily
import com.zgy.kotlinapplication.extension.view.roundImage
import com.zgy.kotlinapplication.logic.model.Tag
import com.zgy.kotlinapplication.logic.model.VideoRelated
import com.zgy.kotlinapplication.ui.video.VideoDetailActivity

class IntroListAdapter : RecyclerView.Adapter<IntroListAdapter.ViewHolder>() {
    private var itemList: List<VideoRelated.Item>? = null

    private lateinit var context: Context

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(itemList: List<VideoRelated.Item>) {
        this.itemList = itemList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_intro_video, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        val count = itemList?.size ?: 0
        Log.e("HomeListAdapter", "$count")
        return count
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemList?.get(position)
        if (item != null) {
            item.data.cover.feed.let { holder.itemBinding.ivIcon.roundImage(it, 12) }
            holder.itemBinding.tvTitle.text = item.data.title
            holder.itemBinding.tvType.text = "#${item.data.category}"
            holder.itemView.setOnClickListener {
                VideoDetailActivity.start(context, item.data.playUrl, item.data.cover.feed, item.data.title, item.data.id)
            }
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemBinding = ItemIntroVideoBinding.bind(itemView)
    }
}