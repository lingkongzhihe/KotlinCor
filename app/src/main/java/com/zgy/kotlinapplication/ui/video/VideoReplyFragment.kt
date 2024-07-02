package com.zgy.kotlinapplication.ui.video

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewStub
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.zgy.kotlinapplication.databinding.FragmentVideoReplyBinding
import com.zgy.kotlinapplication.ui.video.adapter.RepliesAdapter
import kotlinx.coroutines.launch

private const val VIDEO_ID = "videoId"

class VideoReplyFragment : Fragment() {
    private var videoId: Long? = 324268

    private var _binding: FragmentVideoReplyBinding? = null

    private val binding get() = _binding!!

    private lateinit var viewModel: VideoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            videoId = it.getLong(VIDEO_ID)
        }
        Log.e(TAG, "videoId = $videoId")
        viewModel = ViewModelProvider(this)[VideoViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentVideoReplyBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = RepliesAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = adapter

        lifecycleScope.launch {
            viewModel.requestVideoReplies(videoId!!).collect {
                val videoReplies = it.itemList.filter { item ->  item.type == "reply"}
                if (videoReplies.isEmpty()) {
                    binding.viewStub.inflate()
                } else {
                    adapter.setItems(videoReplies)
                }
            }
        }
    }

    companion object {
        private const val TAG = "VideoReplyFragment"

        @JvmStatic
        fun newInstance(videoId: Long) =
            VideoReplyFragment().apply {
                arguments = Bundle().apply {
                    putLong(VIDEO_ID, videoId)
                }
            }
    }
}