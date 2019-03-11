package com.tsovedenski.flickrgallery

import com.tsovedenski.flickrgallery.common.CoroutineContextProvider
import com.tsovedenski.flickrgallery.features.viewer.ViewerAdapter
import com.tsovedenski.flickrgallery.features.viewer.ViewerContract
import com.tsovedenski.flickrgallery.features.viewer.ViewerEvent
import com.tsovedenski.flickrgallery.features.viewer.ViewerPresenter
import io.mockk.*
import kotlinx.coroutines.Dispatchers
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

/**
 * Created by Tsvetan Ovedenski on 11/03/19.
 */
class ViewerPresenterTest {

    private val view: ViewerContract.View = mockk(relaxed = true)

    private val model: ViewerContract.ViewModel = mockk(relaxed = true)

    private val adapter: ViewerAdapter = mockk()

    private val initialPosition = 3

    private val contextProvider: CoroutineContextProvider = mockk()

    private val presenter = ViewerPresenter(
        view,
        model,
        adapter,
        initialPosition,
        contextProvider
    )

    @BeforeEach
    fun setup() {
        clearAllMocks()
        every { contextProvider.provide() } returns Dispatchers.Unconfined
    }

    @Test
    fun setsInitialPositionWhenNotLoaded() {
        every { model.isLoaded() } returns false

        presenter.onChanged(ViewerEvent.OnResume)

        verify { view.setPosition(initialPosition) }
    }

    @Test
    fun doesNotSetInitialPositionWhenLoaded() {
        every { model.isLoaded() } returns true

        presenter.onChanged(ViewerEvent.OnResume)

        verify(inverse = true) { view.setPosition(initialPosition) }
    }
}