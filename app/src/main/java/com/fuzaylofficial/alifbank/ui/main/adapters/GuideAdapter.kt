package com.fuzaylofficial.alifbank.ui.main.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.ConcatAdapter
import com.fuzaylofficial.alifbank.R
import com.fuzaylofficial.alifbank.databinding.RecyclerViewItemBinding
import com.fuzaylofficial.alifbank.models.Guides

class GuideAdapter(diffCallback: GuideDiffUtillCallback) : PagingDataAdapter<Guides.Guide, GuideViewHolder>(diffCallback) {
    override fun onBindViewHolder(holder: GuideViewHolder, position: Int) {
        getItem(position)?.let { holder.Bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuideViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: RecyclerViewItemBinding = DataBindingUtil.inflate(inflater,
            R.layout.recycler_view_item, parent, false)
        return GuideViewHolder(binding)
    }

    fun withMySpecificFooter(
        footer: LoadStateAdapter<*>
    ): ConcatAdapter {
        addLoadStateListener { loadStates ->
            footer.loadState = when (loadStates.refresh) {
                is LoadState.NotLoading -> loadStates.append
                else -> loadStates.refresh
            }
        }
        return ConcatAdapter(this, footer)
    }

}