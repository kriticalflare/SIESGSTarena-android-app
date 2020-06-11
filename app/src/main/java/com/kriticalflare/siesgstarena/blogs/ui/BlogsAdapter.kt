package com.kriticalflare.siesgstarena.blogs.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.marlonlom.utilities.timeago.TimeAgo
import com.google.android.material.chip.Chip
import com.kriticalflare.siesgstarena.R
import com.kriticalflare.siesgstarena.blogs.usecase.ReadBlogUsecase
import com.kriticalflare.siesgstarena.databinding.BlogItemBinding
import com.kriticalflare.siesgstarena.models.Blog
import java.util.Locale
import org.threeten.bp.Instant
import org.threeten.bp.ZoneId
import org.threeten.bp.format.DateTimeFormatter

class BlogsAdapter(private val data: List<Blog>, private val context: Context) : RecyclerView.Adapter<BlogsAdapter.BlogsViewHolder>() {

    lateinit var binding: BlogItemBinding

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BlogsViewHolder {
        binding = BlogItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BlogsViewHolder(binding.root, context)
    }

    override fun getItemCount(): Int {
        return data.count()
    }

    override fun onBindViewHolder(holder: BlogsViewHolder, position: Int) {
        holder.bind(data[position])
        holder.itemView.setOnClickListener {
            ReadBlogUsecase(data[position], context).openCustomTab()
        }
    }

    class BlogsViewHolder(itemView: View, private val context: Context) : RecyclerView.ViewHolder(itemView) {
        private val binding = BlogItemBinding.bind(itemView)

        fun bind(data: Blog) {

            val dateFormatter = DateTimeFormatter.ofPattern(
                "EEE dd/MM/yy H:mm a",
                Locale.ENGLISH
            )

            val formattedCreatedAt: String = Instant.parse(data.createdAt)
                .atZone(ZoneId.systemDefault())
                .format(dateFormatter)

            val recentActivityEpochMilli = Instant.parse(data.updatedAt)
                .atZone(ZoneId.systemDefault())
                .toEpochSecond().times(1000)

            binding.blogName.text = data.title
            binding.blogAuthor.text = data.owner.name
            binding.blogPostedDate.text = context.getString(R.string.blogs_posted_on, formattedCreatedAt)
            binding.blogRecentActivity.text = context.getString(R.string.blogs_recent_activity, TimeAgo.using(recentActivityEpochMilli))
            binding.blogUpvoteCount.text = data.upvotes.toString()
            binding.blogDownvoteCount.text = data.downvotes.toString()

            binding.blogTagChipGroup.removeAllViews()
            for (tag in data.tags.filter {
                it.isNotEmpty()
            }) {
                binding.blogTagChipGroup.addView(
                    Chip(context).apply {
                        text = tag
                        isCloseIconVisible = false
                    }
                )
            }
        }
    }
}
