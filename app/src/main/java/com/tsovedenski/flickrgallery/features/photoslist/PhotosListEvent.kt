package com.tsovedenski.flickrgallery.features.photoslist

/**
 * Created by Tsvetan Ovedenski on 10/03/19.
 */
sealed class PhotosListEvent {
    object OnStart : PhotosListEvent()
    object OnResume : PhotosListEvent()
    object OnDestroy : PhotosListEvent()
    object OnRefresh : PhotosListEvent()
    object OnSearch : PhotosListEvent()
    data class OnSearchQuery(val query: String) : PhotosListEvent()
    data class OnPhotoSelected(val position: Int) : PhotosListEvent()
    object ChangeViewToGridLayout : PhotosListEvent()
    object ChangeViewToCardLayout : PhotosListEvent()
}