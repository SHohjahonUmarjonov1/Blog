package com.example.uzblog.model

import java.io.Serializable

data class UserModel(
    var id: String,
    var title: String,
    var firstName: String,
    var lastName: String,
    var picture: String
):Serializable
