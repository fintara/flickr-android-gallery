package com.tsovedenski.flickrgallery.features.photoslist

import androidx.annotation.StringRes
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
        fun restoreScrollPosition()
        fun openViewer(photos: List<FlickrPhoto>, position: Int)
        fun openSearch(initialQuery: String)
        fun showMessage(@StringRes resId: Int)
        fun showLoading()
        fun hideLoading()
    }

    interface ViewModel {
        fun isLoaded(): Boolean
        fun setLoaded(value: Boolean)
        fun getPhotos(): List<FlickrPhoto>
        fun setPhotos(list: List<FlickrPhoto>)
        fun getViewType(): ViewType
        fun setViewType(type: ViewType)
        fun getSearchQuery(): String
        fun setSearchQuery(value: String)
    }
}

enum class ViewType {
    Grid,
    Card
}