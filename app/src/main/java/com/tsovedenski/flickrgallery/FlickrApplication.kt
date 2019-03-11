package com.tsovedenski.flickrgallery

import android.app.Application
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule

class FlickrApplication : Application()

@GlideModule
class FlickrGlideModule : AppGlideModule()