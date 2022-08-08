package com.example.uzblog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.inflate
import android.widget.Toast
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import com.example.uzblog.Retrofit.BaseResponse
import com.example.uzblog.Retrofit.Networking
import com.example.uzblog.adapter.UserPostAdapter
import com.example.uzblog.databinding.ActivityPostsBinding
import com.example.uzblog.databinding.PostsListBinding
import com.example.uzblog.model.PostModel
import com.example.uzblog.model.UserModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostsActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener {
    private lateinit var binding: ActivityPostsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding=ActivityPostsBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.swipe.setOnRefreshListener(this)
        binding.swipe.isRefreshing=true
        loadUserPost()
    }
    private fun loadUserPost(){
        val user= intent?.getSerializableExtra("user") as UserModel
        Glide.with(this).load(user.picture).into(binding.icon)
        binding.name.text=user.firstName
        Networking.api.getUserPost(user.id).enqueue(object :Callback<BaseResponse<List<PostModel>>>{
            override fun onResponse(
                call: Call<BaseResponse<List<PostModel>>>,
                response: Response<BaseResponse<List<PostModel>>>
            ) {
                binding.swipe.isRefreshing=false
                binding.recycler.adapter=UserPostAdapter(response.body()?.data?: emptyList())
            }

            override fun onFailure(call: Call<BaseResponse<List<PostModel>>>, t: Throwable) {
                Toast.makeText(this@PostsActivity, "Error occurred", Toast.LENGTH_SHORT).show()
            }

        })
    }

    override fun onRefresh() {
        loadUserPost()
    }
}