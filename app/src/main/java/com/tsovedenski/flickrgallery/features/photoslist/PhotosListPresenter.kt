package com.tsovedenski.flickrgallery.features.photoslist

import androidx.lifecycle.Observer
import com.tsovedenski.flickrgallery.common.CoroutineContextProvider
import com.tsovedenski.flickrgallery.common.Try
import com.tsovedenski.flickrgallery.domain.FlickrService
import com.tsovedenski.flickrgallery.domain.models.FlickrPhoto
import com.tsovedenski.flickrgallery.features.common.Presenter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * Created by Tsvetan Ovedenski on 10/03/19.
 */
class PhotosListPresenter (
    private val view: PhotosListContract.View,
    private val model: PhotosListContract.ViewModel,
    private val service: FlickrService,
    private val adapter: PhotosListAdapter,
    private val contextProvider: CoroutineContextProvider
) : Presenter(),
    CoroutineScope,
    Observer<PhotosListEvent>
{
    override val coroutineContext get() = contextProvider.provide() + job

    init {
        job = Job()
    }

    override fun onChanged(t: PhotosListEvent) = when (t) {
        PhotosListEvent.OnStart -> onStart()
        PhotosListEvent.OnResume -> onResume()
        PhotosListEvent.OnDestroy -> onDestroy()
        PhotosListEvent.ChangeViewToGridLayout -> changeViewType(ViewType.Grid)
        PhotosListEvent.ChangeViewToCardLayout -> changeViewType(ViewType.Card)
    }

    private fun onStart() {
        adapter.setObserver(this)
        view.setAdapter(adapter)
        view.setObserver(this)
    }

    private fun onResume() {
        restore()

        if (!model.isLoaded()) {
            loadPhotos()
        }
    }

    private fun onDestroy() {
        job.cancel()
    }

    private fun loadPhotos() = launch {
        val result = Try(service::getPhotos)

        result.fold(
            left = { println("TODO: Error") },
            right = {
                model.setLoaded(true)
                setPhotos(it)
            }
        )
    }

    private fun setPhotos(list: List<FlickrPhoto>) {
        model.setPhotos(list)
        adapter.submitList(list)
    }

    private fun changeViewType(type: ViewType) {
        view.setViewType(type)
        model.setViewType(type)
        adapter.viewType = type
        adapter.notifyDataSetChanged()
    }

    private fun restore() {
        setPhotos(model.getPhotos())
        changeViewType(model.getViewType())
    }
}