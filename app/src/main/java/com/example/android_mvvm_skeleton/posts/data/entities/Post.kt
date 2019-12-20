package com.example.android_mvvm_skeleton.posts.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.GsonBuilder
import com.google.gson.annotations.SerializedName

@Entity(tableName = "table_posts")
data class Post(
    @PrimaryKey
    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("body")
    val body: String,

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("userId")
    val userId: Int
) {
    override fun toString(): String = GsonBuilder().serializeNulls().create().toJson(this)
}