package com.example.uzblog.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.uzblog.R
import com.example.uzblog.databinding.PostsListBinding
import com.example.uzblog.databinding.UsersListBinding
import com.example.uzblog.model.PostModel
import com.example.uzblog.model.UserModel

class PostAdapter(val posts: List<PostModel>,val onClick:(post:PostModel)->Unit ):RecyclerView.Adapter<PostAdapter.ItemHolder>() {
    inner class ItemHolder(private var binding: PostsListBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(post:PostModel,position: Int){
            binding.title.text=post.text
            binding.data.text=post.publishDate
            Glide.with(binding.postImage.context).load(post.image).into(binding.postImage)
            binding.root.setOnClickListener {
                onClick(post)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)=ItemHolder(
        PostsListBinding.inflate(LayoutInflater.from(parent.context), parent,false)
    )

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bind(posts[position],position)
    }

    override fun getItemCount()=posts.size
}