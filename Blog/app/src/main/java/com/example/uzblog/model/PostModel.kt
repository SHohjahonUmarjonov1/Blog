package com.example.uzblog.model

import android.provider.ContactsContract
import java.io.Serializable

data class PostModel(
    val id: String,
    val text: String,
    val image: String,
    val likes: Number,
    val link: String,
    val tags: List<String>,
    val publishDate: String,
    val owner: UserModel,
):Serializable
