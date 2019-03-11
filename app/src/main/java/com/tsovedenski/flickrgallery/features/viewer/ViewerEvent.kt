package com.tsovedenski.flickrgallery.features.viewer

/**
 * Created by Tsvetan Ovedenski on 11/03/19.
 */
sealed class ViewerEvent {
    object OnStart : ViewerEvent()
    object OnResume : ViewerEvent()
    object OnDestroy : ViewerEvent()
}