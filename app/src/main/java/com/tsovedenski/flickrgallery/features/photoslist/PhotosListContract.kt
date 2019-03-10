package com.tsovedenski.flickrgallery.features.photoslist

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ListAdapter
import com.tsovedenski.flickrgallery.domain.models.FlickrPhoto

/**
 * Created by Tsvetan Ovedenski on 10/03/19.
 */
interface PhotosListContract {
    interface View {
        fun setObserver(observer: Observer<PhotosListEvent>)
        fun setAdapter(adapter: ListAdapter<FlickrPhoto, PhotosListAdapter.PhotoViewHolder>)
        fun setViewType(type: ViewType)
    }

    interface ViewModel {
        fun getPhotos(): List<FlickrPhoto>
        fun setPhotos(list: List<FlickrPhoto>)
        fun getViewType(): ViewType
        fun setViewType(type: ViewType)
    }
}

enum class ViewType {
    Grid,
    Card
}