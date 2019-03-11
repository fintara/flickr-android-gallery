package com.tsovedenski.flickrgallery.features.viewer

import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.request.target.Target
import com.ortiz.touchview.TouchImageView
import com.tsovedenski.flickrgallery.GlideApp
import com.tsovedenski.flickrgallery.domain.models.FlickrPhoto

/**
 * Created by Tsvetan Ovedenski on 11/03/19.
 */
class ViewerAdapter(
    initialItems: List<FlickrPhoto> = emptyList()
) : PagerAdapter() {

    private val items = initialItems.toMutableList()

    override fun isViewFromObject(view: View, obj: Any) = view == obj

    override fun getCount() = items.size

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val item = items[position]

        val image = TouchImageView(container.context)

        GlideApp.with(container.context)
            .load(item.imageUrl)
            .override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
            .thumbnail(
                GlideApp
                    .with(container.context)
                    .load(item.thumbUrl)
                    .override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
            )
            .into(image)

        container.addView(
            image,
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        )

        return image
    }

    override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
        container.removeView(obj as View)
    }
}