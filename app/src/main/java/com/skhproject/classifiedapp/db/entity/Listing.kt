package com.skhproject.classifiedapp.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Listing")
class Listing(
    @PrimaryKey @ColumnInfo(name = "uid") val uid: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "price") val price: String,
    @ColumnInfo(name = "image_ids") val image_ids: List<String>,
    @ColumnInfo(name = "image_urls") val image_urls: List<String>,
    @ColumnInfo(name = "image_urls_thumbnails") val image_urls_thumbnails: List<String>,
    @ColumnInfo(name = "created_at") val created_at: String
)