package com.tsovedenski.flickrgallery.features.photoslist

import androidx.lifecycle.Observer
import com.tsovedenski.flickrgallery.common.Try
import com.tsovedenski.flickrgallery.domain.FlickrService
import com.tsovedenski.flickrgallery.features.common.Presenter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * Created by Tsvetan Ovedenski on 10/03/19.
 */
class PhotosListPresenter (
    private val view: PhotosListContract.View,
    private val model: PhotosListContract.ViewModel,
    private val service: FlickrService,
    private val adapter: PhotosListAdapter
) : Presenter(),
    CoroutineScope,
    Observer<PhotosListEvent>
{
    override val coroutineContext get() = Dispatchers.Main + job

    init {
        job = Job()
    }

    override fun onChanged(t: PhotosListEvent) = when (t) {
        PhotosListEvent.OnStart -> onStart()
        PhotosListEvent.OnResume -> onResume()
        PhotosListEvent.OnDestroy -> onDestroy()
        PhotosListEvent.ChangeViewToGridLayout -> changeViewToGrid()
        PhotosListEvent.ChangeViewToCardLayout -> changeViewToCard()
    }

    private fun onStart() {
        adapter.setObserver(this)
        view.setAdapter(adapter)
        view.setObserver(this)
    }

    private fun onResume() {
        loadPhotos()
    }

    private fun onDestroy() {
        job.cancel()
    }

    private fun loadPhotos() = launch {
        val result = Try(service::getPhotos)

        println(result)

        result.fold(
            left = { println("TODO: Error") },
            right = {
                model.setPhotos(it)
                adapter.submitList(it)
                adapter.notifyDataSetChanged()
//                changeViewToCard()
                changeViewToGrid()
            }
        )
    }

    private fun changeViewToGrid() {
        view.setViewType(ViewType.Grid)
        model.setViewType(ViewType.Grid)
        adapter.viewType = ViewType.Grid
        adapter.notifyDataSetChanged()
    }

    private fun changeViewToCard() {
        view.setViewType(ViewType.Card)
        model.setViewType(ViewType.Card)
        adapter.viewType = ViewType.Card
        adapter.notifyDataSetChanged()
    }
}