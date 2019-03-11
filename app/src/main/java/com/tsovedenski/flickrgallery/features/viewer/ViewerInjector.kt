package com.tsovedenski.flickrgallery.features.viewer

import android.app.Application
import androidx.lifecycle.ViewModelProviders
import com.tsovedenski.flickrgallery.common.CoroutineContextProviderImpl
import com.tsovedenski.flickrgallery.domain.models.FlickrPhoto

/**
 * Created by Tsvetan Ovedenski on 11/03/19.
 */
class ViewerInjector (
    private val application: Application
) {

    fun attachPresenter(view: ViewerView, photos: List<FlickrPhoto>, position: Int): ViewerPresenter {
        val presenter = ViewerPresenter(
            view,
            ViewModelProviders.of(view).get(ViewerViewModel::class.java),
            ViewerAdapter(photos),
            photos,
            position,
            CoroutineContextProviderImpl
        )
        view.setObserver(presenter)
        return presenter
    }

}