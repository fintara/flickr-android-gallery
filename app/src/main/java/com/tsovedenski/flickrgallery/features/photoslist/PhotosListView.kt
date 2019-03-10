package com.tsovedenski.flickrgallery.features.photoslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tsovedenski.flickrgallery.R
import com.tsovedenski.flickrgallery.domain.models.FlickrPhoto

/**
 * Created by Tsvetan Ovedenski on 10/03/19.
 */
class PhotosListView : Fragment(), PhotosListContract.View {

    private val event = MutableLiveData<PhotosListEvent>()

    private lateinit var photosList: RecyclerView

    override fun setObserver(observer: Observer<PhotosListEvent>)
            = event.observeForever(observer)

    override fun setAdapter(adapter: ListAdapter<FlickrPhoto, PhotosListAdapter.PhotoViewHolder>) {
        photosList.adapter = adapter
    }

    override fun setViewType(type: ViewType) {
        photosList.layoutManager = when (type) {
            ViewType.Grid -> GridLayoutManager(activity, 2, RecyclerView.VERTICAL, false)
            ViewType.Card -> LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        }
    }

    override fun onStart() {
        super.onStart()
        event.value = PhotosListEvent.OnStart
    }

    override fun onResume() {
        super.onResume()
        event.value = PhotosListEvent.OnResume
    }

    override fun onDestroy() {
        super.onDestroy()
        event.value = PhotosListEvent.OnDestroy
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_photos_list, container, false)

        photosList = view.findViewById(R.id.photos_list)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        event.value = PhotosListEvent.OnStart
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {
        fun newInstance() = PhotosListView()
    }
}