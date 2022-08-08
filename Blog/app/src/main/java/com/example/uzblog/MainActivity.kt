package com.example.uzblog

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.uzblog.Retrofit.BaseResponse
import com.example.uzblog.Retrofit.Networking
import com.example.uzblog.adapter.PostAdapter
import com.example.uzblog.adapter.UserAdapter
import com.example.uzblog.databinding.ActivityMainBinding
import com.example.uzblog.model.PostModel
import com.example.uzblog.model.UserModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.userList.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.postList.layoutManager = LinearLayoutManager(this)
        binding.swipe.setOnRefreshListener(this)
        binding.swipe.isRefreshing = true
        loadUser()
        loadPosts()
    }

    private fun loadUser() {
        Networking.api.getUser().enqueue(object : Callback<BaseResponse<List<UserModel>>> {
            override fun onResponse(
                call: Call<BaseResponse<List<UserModel>>>,
                response: Response<BaseResponse<List<UserModel>>>
            ) {
                binding.swipe.isRefreshing = false
                val userAdapter = UserAdapter(response.body()?.data ?: emptyList(), ::onClick)
                binding.userList.adapter = userAdapter
            }

            override fun onFailure(call: Call<BaseResponse<List<UserModel>>>, t: Throwable) {
                binding.swipe.isRefreshing = false
                Toast.makeText(this@MainActivity, "Error occurred", Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun loadPosts() {
        Networking.api.getPost().enqueue(object : Callback<BaseResponse<List<PostModel>>> {
            override fun onResponse(
                call: Call<BaseResponse<List<PostModel>>>,
                response: Response<BaseResponse<List<PostModel>>>
            ) {
                val postAdapter = PostAdapter(response.body()?.data ?: emptyList(),::onPostClick)
                binding.postList.adapter = postAdapter
            }

            override fun onFailure(call: Call<BaseResponse<List<PostModel>>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Error occurred", Toast.LENGTH_SHORT).show()
            }

        })
    }

    fun onClick(user: UserModel) {
        val intent = Intent(this, PostsActivity::class.java)
        intent.putExtra("user", user)
        startActivity(intent)
    }

    fun onPostClick(post: PostModel) {
        val intent = Intent(this, PostIdActivity::class.java)
        intent.putExtra("post", post)
        startActivity(intent)
    }

    override fun onRefresh() {
        loadUser()
        loadPosts()
    }
}