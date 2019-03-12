package com.tsovedenski.flickrgallery.features.photoslist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tsovedenski.flickrgallery.domain.models.FlickrPhoto

/**
 * Created by Tsvetan Ovedenski on 10/03/19.
 */
class PhotosListViewModel (
    private val loaded: MutableLiveData<Boolean> = MutableLiveData(),
    private val photos: MutableLiveData<List<FlickrPhoto>> = MutableLiveData(),
    private val viewType: MutableLiveData<ViewType> = MutableLiveData(),
    private val searchQuery: MutableLiveData<String> = MutableLiveData()
) : ViewModel(),
    PhotosListContract.ViewModel {

    init {
        loaded.value = false
        photos.value = emptyList()
        viewType.value = ViewType.Grid
        searchQuery.value = ""
    }

    override fun isLoaded(): Boolean {
        return loaded.value!!
    }

    override fun setLoaded(value: Boolean) {
        loaded.value = value
    }

    override fun getPhotos(): List<FlickrPhoto> {
        return photos.value!!
    }

    override fun setPhotos(list: List<FlickrPhoto>) {
        photos.value = list
    }

    override fun getViewType(): ViewType {
        return viewType.value!!
    }

    override fun setViewType(type: ViewType) {
        viewType.value = type
    }

    override fun getSearchQuery(): String {
        return searchQuery.value!!
    }

    override fun setSearchQuery(value: String) {
        searchQuery.value = value
    }
}