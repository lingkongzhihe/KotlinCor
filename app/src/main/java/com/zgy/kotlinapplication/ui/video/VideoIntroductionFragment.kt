package com.zgy.kotlinapplication.ui.video

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.zgy.kotlinapplication.R
import com.zgy.kotlinapplication.databinding.FragmentVideoIntroductionBinding
import com.zgy.kotlinapplication.extension.view.circleImage
import com.zgy.kotlinapplication.ui.video.adapter.IntroListAdapter
import kotlinx.coroutines.launch

private const val VIDEO_ID = "videoId"

class VideoIntroductionFragment : Fragment() {
    private var videoId: Long? = 324268

    private lateinit var viewModel: VideoViewModel

    private var _binding: FragmentVideoIntroductionBinding? = null

    private val binding get() = _binding!!

    private lateinit var adapter : IntroListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[VideoViewModel::class.java]
        arguments?.let {
            videoId = it.getLong(VIDEO_ID)
        }
        Log.e(TAG, "videoId = $videoId")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        videoId?.let { requestVideoDetail(it) }
        adapter = IntroListAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = adapter
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentVideoIntroductionBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    private fun requestVideoDetail(videoId: Long) {
        if (videoId == -1L) return
        lifecycleScope.launch {
            viewModel.requestVideoDetail(videoId).collect{
                it.videoBeanForClient?.apply {
                    author.icon?.let { it1 -> binding.ivHeader.circleImage(it1) }
                    binding.tvAuthorName.text = author.name
                    binding.tvAuthorVideoNum.text = String.format("%s视频", author.videoNum)
                    binding.tvTitle.text = title
                    binding.tvDesc.text = description
                    binding.tvShareCount.text = consumption.shareCount.toString()
                    binding.tvCollectCount.text = "${consumption.collectionCount}"
                    binding.tvReplyCount.text = "${consumption.replyCount}"
                    binding.tvRealCount.text = "${consumption.realCollectionCount}"
                    val pAct: VideoDetailActivity = activity as VideoDetailActivity
                    pAct.setReplyCount(consumption.replyCount)
                }
                it.videoRelated?.apply {
                    adapter.setItems(it.videoRelated.itemList)
                }
            }
        }
    }

    companion object {
        private const val TAG = "VideoIntroductionFragment"

        @JvmStatic
        fun newInstance(videoId: Long) =
            VideoIntroductionFragment().apply {
                arguments = Bundle().apply {
                    putLong(VIDEO_ID, videoId)
                }
            }
    }
}