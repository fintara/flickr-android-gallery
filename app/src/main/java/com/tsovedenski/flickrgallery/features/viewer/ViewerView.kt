package com.tsovedenski.flickrgallery.features.viewer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.tsovedenski.flickrgallery.R

/**
 * Created by Tsvetan Ovedenski on 11/03/19.
 */
class ViewerView : Fragment(), ViewerContract.View {

    private val event = MutableLiveData<ViewerEvent>()

    private lateinit var viewPager: ViewPager

    override fun setObserver(observer: Observer<ViewerEvent>) {
        event.observeForever(observer)
    }

    override fun setAdapter(adapter: PagerAdapter) {
        viewPager.adapter = adapter
    }

    override fun setPosition(position: Int) {
        viewPager.currentItem = position
    }

    override fun onStart() {
        super.onStart()
        event.value = ViewerEvent.OnStart
    }

    override fun onResume() {
        super.onResume()
        event.value = ViewerEvent.OnResume
    }

    override fun onDestroy() {
        super.onDestroy()
        event.value = ViewerEvent.OnDestroy
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_viewer, container, false)

        viewPager = view.findViewById(R.id.viewpager)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        event.value = ViewerEvent.OnStart
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {
        fun newInstance() = ViewerView()
    }
}