package com.kriticalflare.siesgstarena.contests.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kriticalflare.siesgstarena.contests.usecase.ReadContestUsecase
import com.kriticalflare.siesgstarena.databinding.ContestItemBinding
import com.kriticalflare.siesgstarena.models.Contest
import java.util.Locale
import org.threeten.bp.Instant
import org.threeten.bp.ZoneId
import org.threeten.bp.format.DateTimeFormatter

class ContestsAdapter(private val data: List<Contest>, private val context: Context) : RecyclerView.Adapter<ContestsAdapter.ContestsViewHolder>() {

    lateinit var binding: ContestItemBinding

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ContestsViewHolder {
        binding = ContestItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ContestsViewHolder(binding.root)
    }

    override fun getItemCount(): Int {
        return data.count()
    }

    override fun onBindViewHolder(holder: ContestsViewHolder, position: Int) {
        holder.bind(data[position])
        holder.itemView.setOnClickListener {
            ReadContestUsecase(data[position], context).openCustomTab()
        }
    }

    class ContestsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ContestItemBinding.bind(itemView)

        fun bind(data: Contest) {
            print(ZoneId.systemDefault())
            val formattedDateAndTime: String = Instant.parse(data.startsAt)
                .atZone(ZoneId.systemDefault())
                .format(
                    DateTimeFormatter.ofPattern(
                        "EEE dd/MM H:mm a",
                        Locale.ENGLISH
                    )
                )
            binding.contestName.text = data.name
            binding.contestDescription.text = data.description
            binding.contestType.text = data.contestType.name
            binding.contestStartDate.text = formattedDateAndTime
        }
    }
}
