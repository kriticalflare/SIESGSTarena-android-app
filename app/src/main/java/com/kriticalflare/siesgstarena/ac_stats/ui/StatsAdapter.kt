package com.kriticalflare.siesgstarena.ac_stats.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kriticalflare.siesgstarena.databinding.StatsItemBinding
import com.kriticalflare.siesgstarena.models.TopStats

class StatsAdapter(private val data: List<TopStats>) : RecyclerView.Adapter<StatsAdapter.StatsViewHolder>() {

    lateinit var binding: StatsItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatsViewHolder {
        binding = StatsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StatsViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: StatsViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class StatsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = StatsItemBinding.bind(itemView)

        fun bind(data: TopStats) {
            binding.statsCardName.text = data.name
            binding.statsLanguage.text = data.language
            binding.statsCardSubmissionCount.text = data.acceptedCount.toString()
        }
    }
}
