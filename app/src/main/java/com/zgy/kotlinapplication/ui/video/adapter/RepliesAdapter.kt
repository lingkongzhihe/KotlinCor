package com.zgy.kotlinapplication.ui.video.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zgy.kotlinapplication.R
import com.zgy.kotlinapplication.databinding.ItemReplyBinding
import com.zgy.kotlinapplication.extension.view.circleHeader
import com.zgy.kotlinapplication.logic.model.VideoReplies
import java.text.SimpleDateFormat
import java.util.Date

class RepliesAdapter : RecyclerView.Adapter<RepliesAdapter.ViewHolder>() {
    private var itemList: List<VideoReplies.Item>? = null

    private lateinit var context: Context

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(itemList: List<VideoReplies.Item>) {
        this.itemList = itemList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_reply, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        val count = itemList?.size ?: 0
        Log.e("RepliesAdapter", "$count")
        return count
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemList?.get(position)
        if (item != null) {
            item.data.user?.avatar?.let { holder.itemBinding.ivHeader.circleHeader(it) }
            Log.e("RepliesAdapter", "avatar = ${item.data.user?.cover}")
            holder.itemBinding.tvAuthorName.text = item.data.user?.nickname
            holder.itemBinding.tvCreateTime.text = formatTime(item.data.createTime)
            holder.itemBinding.tvMessage.text = item.data.message
            holder.itemBinding.tvLikeCount.text = "${item.data.likeCount}"
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemBinding = ItemReplyBinding.bind(itemView)
    }

    @SuppressLint("SimpleDateFormat")
    private fun formatTime(time: Long): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm")
        return sdf.format(Date(time))
    }
}