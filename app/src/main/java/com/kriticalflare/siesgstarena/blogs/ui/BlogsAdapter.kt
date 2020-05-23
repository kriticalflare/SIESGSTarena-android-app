package com.kriticalflare.siesgstarena.blogs.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.kriticalflare.siesgstarena.R
import com.kriticalflare.siesgstarena.databinding.BlogItemBinding
import com.kriticalflare.siesgstarena.models.Blog
import org.threeten.bp.Instant
import org.threeten.bp.ZoneId
import org.threeten.bp.format.DateTimeFormatter
import java.util.*

class BlogsAdapter(private val data: List<Blog>, private val context: Context): RecyclerView.Adapter<BlogsAdapter.BlogsViewHolder>(){

    lateinit var binding: BlogItemBinding

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BlogsViewHolder {
        binding = BlogItemBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return  BlogsViewHolder(binding.root,context)
    }

    override fun getItemCount(): Int {
        return data.count()
    }

    override fun onBindViewHolder(holder: BlogsViewHolder, position: Int) {
        return holder.bind(data[position])
    }

    class BlogsViewHolder(itemView: View, private val context: Context): RecyclerView.ViewHolder(itemView){
        private val binding = BlogItemBinding.bind(itemView)

        fun bind(data: Blog) {
            print(ZoneId.systemDefault())
            val formattedCreatedAt: String = Instant.parse(data.createdAt)
                .atZone(ZoneId.systemDefault())
                .format(
                    DateTimeFormatter.ofPattern(
                        "EEE dd/MM/yy H:mm a",
                        Locale.ENGLISH
                    )
                )
            val formattedUpdatedAt: String = Instant.parse(data.updatedAt)
                .atZone(ZoneId.systemDefault())
                .format(
                    DateTimeFormatter.ofPattern(
                        "EEE dd/MM/yy H:mm a",
                        Locale.ENGLISH
                    )
                )
            binding.blogName.text = data.title
            binding.blogAuthor.text = data.owner.name
            binding.blogPostedDate.text = context.getString(R.string.blogs_posted_on, formattedCreatedAt)
            binding.blogRecentActivity.text = context.getString(R.string.blogs_recent_activity, formattedUpdatedAt)
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