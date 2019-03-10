package com.tsovedenski.flickrgallery.features.photoslist

import android.app.Application
import androidx.lifecycle.ViewModelProviders
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.tsovedenski.flickrgallery.domain.FlickrApi
import com.tsovedenski.flickrgallery.domain.FlickrService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Tsvetan Ovedenski on 10/03/19.
 */
class PhotosListInjector (
    private val application: Application
) {

    private val flickrApi: FlickrApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.flickr.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
            .create(FlickrApi::class.java)
    }

    private val flickrService by lazy {
        FlickrService(flickrApi)
    }

    fun attachPresenter(view: PhotosListView): PhotosListPresenter {
        val presenter = PhotosListPresenter(
            view,
            ViewModelProviders.of(view).get(PhotosListViewModel::class.java),
            flickrService,
            PhotosListAdapter(application)
        )
        view.setObserver(presenter)
        return presenter
    }
}