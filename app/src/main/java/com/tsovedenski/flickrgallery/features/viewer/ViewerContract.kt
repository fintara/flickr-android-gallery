package com.tsovedenski.flickrgallery.features.viewer

import androidx.lifecycle.Observer
import androidx.viewpager.widget.PagerAdapter

/**
 * Created by Tsvetan Ovedenski on 11/03/19.
 */
interface ViewerContract {
    interface View {
        fun setObserver(observer: Observer<ViewerEvent>)
        fun setAdapter(adapter: PagerAdapter)
        fun setPosition(position: Int)
    }

    interface ViewModel {
        fun isLoaded(): Boolean
        fun setLoaded(value: Boolean)
        fun getPosition(): Int
        fun setPosition(value: Int)
    }
}