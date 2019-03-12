package com.tsovedenski.flickrgallery.features.photoslist

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
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
import com.tsovedenski.flickrgallery.features.viewer.ViewerActivity
import com.tsovedenski.flickrgallery.showToast
import kotlinx.android.synthetic.main.fragment_photos_list.*

/**
 * Created by Tsvetan Ovedenski on 10/03/19.
 */
class PhotosListView : Fragment(), PhotosListContract.View {

    private val event = MutableLiveData<PhotosListEvent>()

    private val gridLayoutManager by lazy {
        GridLayoutManager(activity, 2, RecyclerView.VERTICAL, false)
    }

    private val cardLayoutManager by lazy {
        LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
    }

    override fun setObserver(observer: Observer<PhotosListEvent>) {
        event.observeForever(observer)
    }

    override fun setAdapter(adapter: ListAdapter<FlickrPhoto, PhotosListAdapter.PhotoViewHolder>) {
        photos_list.adapter = adapter

    }

    override fun setViewType(type: ViewType) {
        photos_list.layoutManager = when (type) {
            ViewType.Grid -> gridLayoutManager
            ViewType.Card -> cardLayoutManager
        }
    }

    override fun restoreScrollPosition() {
        if (layoutManagerState != null) {
            photos_list.layoutManager?.onRestoreInstanceState(layoutManagerState)
        }
    }

    override fun showMessage(resId: Int) {
        showToast(resId)
    }

    override fun showLoading() {
        photos_list.visibility = View.INVISIBLE
        spinner.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        photos_list.visibility = View.VISIBLE
        spinner.visibility = View.GONE
    }

    override fun openViewer(photos: List<FlickrPhoto>, position: Int) {
        val intent = Intent(activity, ViewerActivity::class.java).apply {
            putParcelableArrayListExtra("photos", ArrayList(photos))
            putExtra("position", position)
        }
        startActivity(intent)
        activity?.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }

    override fun onStart() {
        super.onStart()
        event.value = PhotosListEvent.OnStart
    }

    override fun onResume() {
        super.onResume()
        event.value = PhotosListEvent.OnResume
    }

    override fun onPause() {
        layoutManagerState = photos_list.layoutManager?.onSaveInstanceState()
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        event.value = PhotosListEvent.OnDestroy
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_photos_list, container, false)
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
            R.id.refresh -> { event.value = PhotosListEvent.OnRefresh; true }
            else -> false
        }
    }

    companion object {
        fun newInstance() = PhotosListView()

        private var layoutManagerState: Parcelable? = null
    }
}