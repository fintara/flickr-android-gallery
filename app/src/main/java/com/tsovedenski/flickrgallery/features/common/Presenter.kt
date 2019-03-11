package com.tsovedenski.flickrgallery.features.common

import androidx.lifecycle.Observer
import com.tsovedenski.flickrgallery.common.CoroutineContextProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job

/**
 * Created by Tsvetan Ovedenski on 10/03/19.
 */
abstract class Presenter <T> (
    contextProvider: CoroutineContextProvider
) : CoroutineScope, Observer<T> {

    val job = Job()
    override val coroutineContext by lazy { contextProvider.provide() + job }

    protected fun onDestroy() {
        job.cancel()
    }
}