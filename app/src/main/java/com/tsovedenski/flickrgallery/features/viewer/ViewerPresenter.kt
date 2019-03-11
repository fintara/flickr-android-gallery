package com.tsovedenski.flickrgallery.features.viewer

import androidx.viewpager.widget.PagerAdapter
import com.tsovedenski.flickrgallery.common.CoroutineContextProvider
import com.tsovedenski.flickrgallery.domain.models.FlickrPhoto
import com.tsovedenski.flickrgallery.features.common.Presenter

/**
 * Created by Tsvetan Ovedenski on 11/03/19.
 */
class ViewerPresenter (
    private val view: ViewerContract.View,
    private val model: ViewerContract.ViewModel,
    private val adapter: PagerAdapter,
    private val photos: List<FlickrPhoto>,
    private val initialPosition: Int,
    contextProvider: CoroutineContextProvider
) : Presenter<ViewerEvent>(contextProvider) {

    override fun onChanged(e: ViewerEvent) = when (e) {
        ViewerEvent.OnStart -> onStart()
        ViewerEvent.OnResume -> onResume()
        ViewerEvent.OnDestroy -> onDestroy()
    }

    private fun onStart() {
        view.setAdapter(adapter)
    }

    private fun onResume() {
        if (!model.isLoaded()) {
            view.setPosition(initialPosition)
            model.setLoaded(true)
        }
    }
}