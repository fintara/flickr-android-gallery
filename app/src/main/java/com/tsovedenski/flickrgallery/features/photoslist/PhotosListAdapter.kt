package com.tsovedenski.flickrgallery.features.photoslist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.tsovedenski.flickrgallery.GlideApp
import com.tsovedenski.flickrgallery.R
import com.tsovedenski.flickrgallery.domain.models.FlickrPhoto


/**
 * Created by Tsvetan Ovedenski on 10/03/19.
 */
class PhotosListAdapter (
    private val context: Context,
    private val event: MutableLiveData<PhotosListEvent> = MutableLiveData()
) : ListAdapter<FlickrPhoto, PhotosListAdapter.PhotoViewHolder>(diffCallback) {

    var viewType: ViewType = ViewType.Card

    fun setObserver(observer: Observer<PhotosListEvent>) = event.observeForever(observer)

    override fun getItemViewType(position: Int): Int = viewType.ordinal

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val (holder, layout) = when (viewType) {
            0 -> Pair(PhotoViewHolder::GridViewHolder, R.layout.row_grid_item)
            1 -> Pair(PhotoViewHolder::CardViewHolder, R.layout.row_card_item)
            else -> throw RuntimeException("Unknown viewType: $viewType")
        }
        return holder(inflater.inflate(layout, parent, false))
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val item = getItem(position)
        holder.bindTo(item, position, event, context)
    }

    sealed class PhotoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        protected fun createSpinner(context: Context) = CircularProgressDrawable(context).apply {
            strokeWidth = 3f
            centerRadius = 30f
            start()
        }

        abstract fun bindTo(
            item: FlickrPhoto,
            position: Int,
            event: MutableLiveData<PhotosListEvent>,
            context: Context
        )

        protected fun ImageView.load(url: String, context: Context) = GlideApp
            .with(context)
            .load(url)
            .placeholder(createSpinner(context))
            .into(this)

        class GridViewHolder(view: View) : PhotoViewHolder(view) {
            private val image: ImageView = view.findViewById(R.id.imageView)

            override fun bindTo(
                item: FlickrPhoto,
                position: Int,
                event: MutableLiveData<PhotosListEvent>,
                context: Context
            ) {
                image.load(item.thumbUrl, context)
                image.setOnClickListener {
                    event.value = PhotosListEvent.OnPhotoSelected(position)
                }
            }
        }

        class CardViewHolder(view: View) : PhotoViewHolder(view) {
            private val image: ImageView = view.findViewById(R.id.imageView)
            private val title: TextView = view.findViewById(R.id.imageTitle)
            private val date: TextView = view.findViewById(R.id.imageDate)
            private val tags: TextView = view.findViewById(R.id.imageTags)

            override fun bindTo(
                item: FlickrPhoto,
                position: Int,
                event: MutableLiveData<PhotosListEvent>,
                context: Context
            ) {
                title.text = item.title
                date.text = item.date
                tags.text = item.tags
                image.load(item.thumbUrl, context)
                image.setOnClickListener {
                    event.value = PhotosListEvent.OnPhotoSelected(position)
                }
            }
        }
    }
}

private val diffCallback = object : DiffUtil.ItemCallback<FlickrPhoto>() {
    override fun areItemsTheSame(oldItem: FlickrPhoto, newItem: FlickrPhoto): Boolean {
        return oldItem.imageUrl == newItem.imageUrl
    }

    override fun areContentsTheSame(oldItem: FlickrPhoto, newItem: FlickrPhoto): Boolean {
        return oldItem.imageUrl == newItem.imageUrl
    }
}