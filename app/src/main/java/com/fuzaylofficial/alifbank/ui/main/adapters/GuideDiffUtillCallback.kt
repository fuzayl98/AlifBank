package com.fuzaylofficial.alifbank.ui.main.adapters

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import com.fuzaylofficial.alifbank.models.Guides

class GuideDiffUtillCallback : DiffUtil.ItemCallback<Guides.Guide>() {

    override fun areItemsTheSame(oldItem: Guides.Guide, newItem: Guides.Guide): Boolean {
        return oldItem.url == newItem.url
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: Guides.Guide, newItem: Guides.Guide): Boolean {
        return oldItem == newItem
    }
}