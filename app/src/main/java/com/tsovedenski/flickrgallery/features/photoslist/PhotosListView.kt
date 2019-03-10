package com.tsovedenski.flickrgallery.features.photoslist

import android.os.Bundle
import android.view.*
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

    private lateinit var photosListView: RecyclerView

    override fun setObserver(observer: Observer<PhotosListEvent>)
            = event.observeForever(observer)

    override fun setAdapter(adapter: ListAdapter<FlickrPhoto, PhotosListAdapter.PhotoViewHolder>) {
        photosListView.adapter = adapter
    }

    override fun setViewType(type: ViewType) {
        photosListView.layoutManager = when (type) {
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

        photosListView = view.findViewById(R.id.photos_list)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        event.value = PhotosListEvent.OnStart
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.photos_list, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.viewtype_grid -> { event.value = PhotosListEvent.ChangeViewToGridLayout; true }
            R.id.viewtype_card -> { event.value = PhotosListEvent.ChangeViewToCardLayout; true }
            else -> false
        }
    }

    companion object {
        fun newInstance() = PhotosListView()
    }
}