package com.example.uzblog.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.uzblog.databinding.UsersListBinding
import com.example.uzblog.model.UserModel

class UserAdapter(var users: List<UserModel>,val onClick:(user:UserModel)->Unit ):RecyclerView.Adapter<UserAdapter.ItemHolder>() {
    inner class ItemHolder(private var binding: UsersListBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(user:UserModel,position: Int){
            binding.userName.text=user.firstName
            Glide.with(binding.userImage.context).load(user.picture).into(binding.userImage)
            binding.root.setOnClickListener {
                    onClick(user)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)=ItemHolder(
        UsersListBinding.inflate(LayoutInflater.from(parent.context), parent,false)
    )

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bind(users[position],position)
    }

    override fun getItemCount()=users.size

}