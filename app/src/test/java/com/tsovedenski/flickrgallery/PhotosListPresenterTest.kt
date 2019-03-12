package com.tsovedenski.flickrgallery

import com.tsovedenski.flickrgallery.common.CoroutineContextProvider
import com.tsovedenski.flickrgallery.domain.FlickrService
import com.tsovedenski.flickrgallery.domain.models.FlickrPhoto
import com.tsovedenski.flickrgallery.features.photoslist.*
import io.mockk.*
import kotlinx.coroutines.Dispatchers
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

/**
 * Created by Tsvetan Ovedenski on 10/03/19.
 */
class PhotosListPresenterTest {

    private val view: PhotosListContract.View = mockk(relaxed = true)

    private val model: PhotosListContract.ViewModel = mockk(relaxed = true)

    private val service: FlickrService = mockk()

    private val adapter: PhotosListAdapter = mockk(relaxed = true)

    private val contextProvider: CoroutineContextProvider = mockk()

    private val presenter = PhotosListPresenter(
        view,
        model,
        service,
        adapter,
        contextProvider
    )

    @BeforeEach
    fun setup() {
        clearAllMocks()
        every { contextProvider.provide() } returns Dispatchers.Unconfined
    }

    @Test
    fun loadsPhotosInitially() {
        val list = listOf(getFlickrPhoto())

        every { model.isLoaded() } returns false
        coEvery { service.getPhotos() } returns list

        presenter.onChanged(PhotosListEvent.OnResume)

        verify { model.setLoaded(true) }
        verify { model.setPhotos(list) }
        verify { adapter.submitList(list) }
    }

    @Test
    fun doesNotLoadPhotosAgain() {
        every { model.isLoaded() } returns true

        presenter.onChanged(PhotosListEvent.OnResume)

        verify(inverse = true) { model.setLoaded(true) }
        coVerify(inverse = true) { service.getPhotos() }
    }

    @Test
    fun showsMessageOnLoadError() {
        every { model.isLoaded() } returns false
        coEvery { service.getPhotos() } throws RuntimeException()

        presenter.onChanged(PhotosListEvent.OnResume)

        verify { view.showMessage(R.string.error_could_not_load) }
        verify(inverse = true) { adapter.submitList(any()) }
        verify(inverse = true) { model.setPhotos(any()) }
    }

    @Test
    fun restoresOnResumeWhenLoaded() {
        every { model.isLoaded() } returns true
        
        val list = listOf(getFlickrPhoto())

        every { model.getPhotos() } returns list
        every { model.getViewType() } returns ViewType.Card
        coEvery { service.getPhotos() } returns list

        presenter.onChanged(PhotosListEvent.OnResume)

        verify { view.setViewType(ViewType.Card) }
        verify { adapter.submitList(list) }
        verify { view.restoreScrollPosition() }
    }

    private fun getFlickrPhoto() = FlickrPhoto(
        "Test",
        "now",
        "tag1 tag2",
        "url_thumb",
        "url_image"
    )
}