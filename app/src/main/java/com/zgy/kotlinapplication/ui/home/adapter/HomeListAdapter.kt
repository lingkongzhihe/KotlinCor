package com.zgy.kotlinapplication.ui.home.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zgy.kotlinapplication.R
import com.zgy.kotlinapplication.databinding.ItemHomeBinding
import com.zgy.kotlinapplication.logic.model.Daily
import com.zgy.kotlinapplication.extension.view.roundImage
import com.zgy.kotlinapplication.ui.video.VideoDetailActivity

class HomeListAdapter : RecyclerView.Adapter<HomeListAdapter.ViewHolder>() {
    private var itemList: List<Daily.Item>? = null

    private lateinit var context: Context

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(itemList: List<Daily.Item>) {
        this.itemList = itemList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_home, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        val count = itemList?.size ?: 0
        Log.e("HomeListAdapter", "$count")
        return count
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemList?.get(position)
        item?.data?.content?.data?.run {
            holder.itemBinding.ivIcon.roundImage(cover.feed, 12)
            holder.itemBinding.tvTitle.text = title
            holder.itemBinding.tvDesc.text = description

            holder.itemView.rootView.setOnClickListener {
                VideoDetailActivity.start(context, playUrl, cover.feed, title, videoId = id)
            }
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemBinding = ItemHomeBinding.bind(itemView)
    }
}