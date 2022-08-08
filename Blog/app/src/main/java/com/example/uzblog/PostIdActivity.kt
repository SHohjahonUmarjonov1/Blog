package com.example.uzblog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import com.example.uzblog.Retrofit.BaseResponse
import com.example.uzblog.Retrofit.Networking
import com.example.uzblog.databinding.ActivityPostIdBinding
import com.example.uzblog.model.PostModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostIdActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener {
    private lateinit var binding: ActivityPostIdBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityPostIdBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.swipe.setOnRefreshListener(this)
        binding.swipe.isRefreshing = true
        loadPostId()
    }

    private fun loadPostId() {
        val post = intent?.getSerializableExtra("post") as PostModel
        Networking.api.getPostId(post.id).enqueue(object : Callback<PostModel> {
            override fun onResponse(call: Call<PostModel>, response: Response<PostModel>) {
                binding.swipe.isRefreshing = false
                setPostId(response.body())
            }

            override fun onFailure(call: Call<PostModel>, t: Throwable) {
                binding.swipe.isRefreshing = false
                Toast.makeText(this@PostIdActivity, "Error occurred", Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun setPostId(post: PostModel?) {

        Glide.with(this).load(post?.image).into(binding.postImage)
        binding.title.text = post?.text
        binding.date.text = post?.publishDate


    }

    override fun onRefresh() {
        loadPostId()
    }
}