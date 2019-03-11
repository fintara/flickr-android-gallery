package com.tsovedenski.flickrgallery.features.viewer

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Created by Tsvetan Ovedenski on 11/03/19.
 */
class ViewerViewModel (
    private val loaded: MutableLiveData<Boolean> = MutableLiveData(),
    private val position: MutableLiveData<Int> = MutableLiveData()
) : ViewModel(), ViewerContract.ViewModel {

    init {
        loaded.value = false
    }

    override fun isLoaded(): Boolean {
        return loaded.value!!
    }

    override fun setLoaded(value: Boolean) {
        loaded.value = value
    }

    override fun getPosition(): Int {
        return position.value ?: 0
    }

    override fun setPosition(value: Int) {
        position.value = value
    }
}