package com.tsovedenski.flickrgallery.features.photoslist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tsovedenski.flickrgallery.domain.models.FlickrPhoto

/**
 * Created by Tsvetan Ovedenski on 10/03/19.
 */
class PhotosListViewModel (
    private val photos: MutableLiveData<List<FlickrPhoto>> = MutableLiveData(),
    private val viewType: MutableLiveData<ViewType> = MutableLiveData()
) : ViewModel(),
    PhotosListContract.ViewModel {

    init {
        photos.value = emptyList()
        viewType.value = ViewType.Grid
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

}