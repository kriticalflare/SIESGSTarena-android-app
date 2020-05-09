package com.kriticalflare.siesgstarena.problemset.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.kriticalflare.siesgstarena.databinding.ProblemItemBinding
import com.kriticalflare.siesgstarena.models.Problem

class ProblemsAdapter(private val data: List<Problem>, private val context: Context) :
    RecyclerView.Adapter<ProblemsAdapter.ProblemsViewHolder>() {

    lateinit var binding: ProblemItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProblemsViewHolder {
        binding = ProblemItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProblemsViewHolder(binding.root, context)
    }

    override fun getItemCount(): Int {
        return data.count()
    }

    override fun onBindViewHolder(holder: ProblemsViewHolder, position: Int) {
        return holder.bind(data = data[position])
    }

    class ProblemsViewHolder(itemView: View, private val context: Context) :
        RecyclerView.ViewHolder(itemView) {

        private val binding = ProblemItemBinding.bind(itemView)

        fun bind(data: Problem) {
            binding.problemName.text = data.name
            binding.problemCode.text = data.code
            binding.problemPoints.text = data.points.toString()
//            Remove all previously added chips , otherwise chips show up multiple times
            binding.problemTagChipGroup.removeAllViews()
            for (tag in data.tags.filter {
                it.isNotEmpty()
            }) {
                binding.problemTagChipGroup.addView(
                    Chip(context).apply {
                        text = tag
                        isCloseIconVisible = false
                    }
                )
            }
        }
    }

}