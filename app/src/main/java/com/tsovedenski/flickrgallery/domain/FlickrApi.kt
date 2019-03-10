package com.tsovedenski.flickrgallery.domain

import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface FlickrApi {

    @GET("/services/feeds/photos_public.gne?format=json&nojsoncallback=1")
    fun getPublicFeedAsync(): Deferred<Response>
}

data class Response (
    val items: List<ResponseItem>
)

data class ResponseItem (
    val title: String,
    val media: MediaSmall,
    val dateTaken: String,
    val published: String,
    val tags: String
)

data class MediaSmall (
    val m: String
)

data class MediaLarge (
    val b: String
)

val MediaSmall.value get() = m
val MediaLarge.value get() = b

fun MediaSmall.toSmall() = this
fun MediaSmall.toLarge() =
    MediaLarge(value.replace("_m.", "_b."))

fun MediaLarge.toSmall() =
    MediaSmall(value.replace("_b.", "_m."))
fun MediaLarge.toLarge() = this