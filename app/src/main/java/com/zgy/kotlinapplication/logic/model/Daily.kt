package com.zgy.kotlinapplication.logic.model

/**
 * 首页-日报列表，响应实体类。
 *
 * @author vipyinzhiwei
 * @since  2020/5/9
 */
data class Daily(val itemList: List<Item>, val count: Int, val total: Int, val nextPageUrl: String?, val adExist: Boolean) : Model() {

    data class Item(val `data`: Data, val type: String, val tag: Any?, val id: Int = 0, val adIndex: Int)

    data class Data(
        val actionUrl: String?,
        val adTrack: Any,
        val backgroundImage: String,
        val content: Content,
        val dataType: String,
        val header: Header,
        val id: Int,
        val rightText: String,
        val subTitle: Any,
        val text: String,
        val titleList: List<String>,
        val type: String,
        val image: String,
    )

    data class Header(
        val actionUrl: String?,
        val cover: Any,
        val description: String,
        val font: Any,
        val icon: String?,
        val iconType: String,
        val id: Int,
        val label: Any,
        val labelList: Any,
        val rightText: Any,
        val showHateVideo: Boolean,
        val subTitle: Any,
        val subTitleFont: Any,
        val textAlign: String,
        val time: Long,
        val title: String
    )

    data class Content(val adIndex: Int, val `data`: FollowCard, val id: Int, val tag: Any, val type: String)

    data class FollowCard(
        val ad: Boolean,
        val adTrack: List<Any>,
        val brandWebsiteInfo: Any,
        val campaign: Any,
        val category: String,
        val collected: Boolean,
        val dataType: String,
        val date: Long,
        val description: String,
        val descriptionEditor: String,
        val descriptionPgc: Any,
        val duration: Int,
        val favoriteAdTrack: Any,
        val id: Long,
        val idx: Int,
        val ifLimitVideo: Boolean,
        val label: Any,
        val labelList: List<Any>,
        val lastViewTime: Any,
        val library: String,
        val playUrl: String,
        val played: Boolean,
        val playlists: Any,
        val promotion: Any,
        val reallyCollected: Boolean,
        val releaseTime: Long?,
        val remark: String,
        val resourceType: String,
        val searchWeight: Int,
        val shareAdTrack: Any,
        val slogan: Any,
        val src: Any,
        val subtitles: List<Any>,
        val thumbPlayUrl: Any,
        val title: String,
        val titlePgc: Any,
        val type: String,
        val waterMarks: Any,
        val webAdTrack: Any,
        val cover: Cover
    )

    data class Cover(val feed: String, val detail: String, val blurred: String, val homepage: String)
}