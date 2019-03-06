package com.tsovedenski.flickrgallery

import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FlickrApi {

    @GET("/services/feeds/photos_public.gne")
    fun getPublicFeed(@Query("format") format: String): Observable<Any>
}