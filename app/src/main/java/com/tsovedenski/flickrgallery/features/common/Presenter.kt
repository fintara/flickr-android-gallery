package com.tsovedenski.flickrgallery.features.common

import kotlinx.coroutines.Job

/**
 * Created by Tsvetan Ovedenski on 10/03/19.
 */
abstract class Presenter {
    protected lateinit var job: Job
}