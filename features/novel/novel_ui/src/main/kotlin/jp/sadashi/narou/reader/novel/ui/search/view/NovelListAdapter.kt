package jp.sadashi.narou.reader.novel.ui.search.view

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import jp.sadashi.narou.reader.novel.core.view.extensions.inflate
import jp.sadashi.narou.reader.novel.domain.dto.NovelSummary
import jp.sadashi.narou.reader.novel.ui.R
import kotlinx.android.synthetic.main.item_novel.view.buttonBookmark
import kotlinx.android.synthetic.main.item_novel.view.item_layout
import kotlinx.android.synthetic.main.item_novel.view.rating
import kotlinx.android.synthetic.main.item_novel.view.title
import kotlinx.android.synthetic.main.item_novel.view.updateAt
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject
import kotlin.properties.Delegates

internal class NovelListAdapter @Inject constructor() :
    RecyclerView.Adapter<NovelListAdapter.ViewHolder>() {

    internal var collection: MutableList<NovelSummary> by Delegates.observable(mutableListOf()) { _, _, _ ->
        notifyDataSetChanged()
    }

    internal var clickListener: (NovelSummary) -> Unit = { _ -> }
    internal var clickBookmarkListener: (NovelSummary) -> Unit = { _ -> }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(parent.inflate(R.layout.item_novel))

    override fun getItemCount() = collection.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val viewModel = collection.getOrNull(position) ?: return
        holder.bind(viewModel, clickListener, clickBookmarkListener)
    }

    fun updateItem(summary: NovelSummary) {
        val index = collection.indexOfFirst { it.novelCode == summary.novelCode }
        if (index == -1) {
            throw IllegalArgumentException("Not found novel at list. NovelCode: ${summary.novelCode}")
        }
        collection[index] = summary
        notifyItemChanged(index)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(
            summary: NovelSummary,
            clickListener: (NovelSummary) -> Unit,
            clickBookmarkListener: (NovelSummary) -> Unit
        ) {
            itemView.title.text = summary.title
            itemView.rating.text = summary.totalRating.toString()
            itemView.updateAt.text =
                SimpleDateFormat("yyyy-MM-dd", Locale.JAPAN).format(summary.novelUpdatedAt)
            itemView.buttonBookmark.isActivated = summary.isBookmark
            itemView.buttonBookmark.setOnClickListener {
                clickBookmarkListener(summary)
            }
            itemView.item_layout.setOnClickListener {
                clickListener(summary)
            }
        }
    }
}