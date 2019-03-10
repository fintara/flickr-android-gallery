package com.tsovedenski.flickrgallery.domain.models

/**
 * Created by Tsvetan Ovedenski on 10/03/19.
 */
data class FlickrPhoto (
    val title: String,
    val date: String,
    val tags: String,
    val thumbUrl: String,
    val imageUrl: String
)