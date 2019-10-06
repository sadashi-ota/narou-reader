package jp.sadashi.narou.reader.novel.ui.search.view

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import jp.sadashi.narou.reader.novel.core.view.extensions.inflate
import jp.sadashi.narou.reader.novel.domain.dto.NovelSummary
import jp.sadashi.narou.reader.novel.ui.R
import kotlinx.android.synthetic.main.item_novel.view.item_layout
import kotlinx.android.synthetic.main.item_novel.view.name
import javax.inject.Inject
import kotlin.properties.Delegates

internal class NovelListAdapter @Inject constructor() :
    RecyclerView.Adapter<NovelListAdapter.ViewHolder>() {

    internal var collection: List<NovelSummary> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    internal var clickListener: (NovelSummary) -> Unit = { _ -> }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(parent.inflate(R.layout.item_novel))

    override fun getItemCount() = collection.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val viewModel = collection.getOrNull(position) ?: return
        holder.bind(viewModel, clickListener)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(summary: NovelSummary, clickListener: (NovelSummary) -> Unit) {
            itemView.name.text = summary.title
            itemView.item_layout.setOnClickListener {
                clickListener(summary)
            }
        }
    }
}