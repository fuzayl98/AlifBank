package com.fuzaylofficial.alifbank.ui.main.adapters

import android.content.Intent
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.fuzaylofficial.alifbank.Web
import com.fuzaylofficial.alifbank.databinding.RecyclerViewItemBinding
import com.fuzaylofficial.alifbank.models.Guides

class GuideViewHolder(private val binding: RecyclerViewItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun Bind(guide: Guides.Guide) {
        binding.guide = guide
        itemView.setOnClickListener {
            val intent = Intent(itemView.context, Web::class.java)
            intent.putExtra("url",guide.url)
            itemView.context.startActivity(intent)
        }
    }

}