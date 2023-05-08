/*
package app.Project_setup.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import app.Project_setup.R
import app.Project_setup.databinding.ItemPostBinding
import app.Project_setup.utils.TimeFormat
import com.bumptech.glide.RequestManager

class PostAdapter(private val context: Context, private val glide: RequestManager, val onItemClick: (Int, Int, Posts.Post) -> Unit) : PagingDataAdapter<Posts.Post, RecyclerView.ViewHolder>(DiffUtils) {

    var MEDIA = 2

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return PostViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_post, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        try {
            val results = getItem(position)!!
          if (results.postType == "media") {
                (holder as PostViewHolder).bind(position)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)!!.postType) {
            "media" -> MEDIA
            else -> 0
        }
    }


    internal inner class PostViewHolder(val binding: ItemPostBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            val data = getItem(position)!!
            val time = TimeFormat.getTimeGap(data.createdAt!!.toLong())
            with(binding) {
                bean = data
                vpMedia.adapter = MediaAdapter(data.media, glide) {
                    onItemClick(1, position, data)
                }
                counterVisible = data.media.size > 1
                tvTime.text = time
                tvMediaCount.text = "1/${data.media.size}"
                ivLike.setOnClickListener {
                    onItemClick(1, position, data)
                }
                ivComment.setOnClickListener {
                    onItemClick(2, position, data)
                }
                llHeader.ivMenu.setOnClickListener {
                    onItemClick(3, position, data)
                }
                tvTag.setOnClickListener {
                    onItemClick(4, position, data)
                }
                llHeader.tvName.setOnClickListener {
                    onItemClick(5, position, data)
                }
                llHeader.ivProfile.setOnClickListener {
                    onItemClick(6, position, data)
                }
            }
            if (data.locationAddress.isNullOrBlank()) {
                binding.llHeader.tvLocation.visibility = View.GONE
            } else {
                binding.llHeader.tvLocation.visibility = View.VISIBLE
            }
            binding.executePendingBindings()

            binding.vpMedia.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    binding.tvMediaCount.text = "${position.plus(1)}/${data.media?.size}"
                    super.onPageSelected(position)
                }
            })
        }
    }

    fun getMediaByPosition(position: Int): List<String> {
        val media = arrayListOf<String>()
        if (itemCount > 0 && position < itemCount) {
            getItem(position)?.let {
                if (!it.media.isNullOrEmpty()) {
                    it.media.forEach {
                        media.add(it.media)
                    }
                }
            }
        }
        return media
    }

    object DiffUtils : DiffUtil.ItemCallback<Posts.Post>() {
        override fun areItemsTheSame(oldItem: Posts.Post, newItem: Posts.Post): Boolean {
            return oldItem.postId == newItem.postId
        }

        override fun areContentsTheSame(oldItem: Posts.Post, newItem: Posts.Post): Boolean {
            return oldItem == newItem
        }

    }

}*/
