package com.tsovedenski.flickrgallery.domain

import com.tsovedenski.flickrgallery.domain.models.FlickrPhoto
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Tsvetan Ovedenski on 10/03/19.
 */
class FlickrService (
    private val api: FlickrApi
) {
    private val sourceFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH)
    private val format = SimpleDateFormat("dd MMMM YYYY, HH:mm", Locale.ENGLISH)

    suspend fun getPhotos(query: String = ""): List<FlickrPhoto> {
        val response = api.getPublicFeedAsync(query).await()
        return response.items.map { FlickrPhoto(
            title = with(it.title.trim()) { if (isBlank()) "Untitled" else this },
            date = format.format(sourceFormat.parse(it.published)),
            tags = it.tags.trim(),
            thumbUrl = it.media.toSmall().value,
            imageUrl = it.media.toLarge().value
        ) }
//        return dummyData
    }

    private val dummyData = listOf(
        FlickrPhoto(
            title="#56 Ford Escort 1300",
            date="2019-03-10T15:34:30Z",
            tags="doningtonstagesrally leicestershire motorsport doningtonpark rally circuit",
            thumbUrl="https://farm8.staticflickr.com/7922/32396791767_da686bc2b6_m.jpg",
            imageUrl="https://farm8.staticflickr.com/7922/32396791767_da686bc2b6_b.jpg"),
        FlickrPhoto(
            title="20190310103156ch01",
            date="2019-03-10T15:34:31Z",
            tags="olden2",
            thumbUrl="https://farm8.staticflickr.com/7855/32396791797_1670cdc1ab_m.jpg",
            imageUrl="https://farm8.staticflickr.com/7855/32396791797_1670cdc1ab_b.jpg"
        ),
        FlickrPhoto(title="",
            date="2019-03-10T15:34:52Z",
            tags="",
            thumbUrl="https://farm8.staticflickr.com/7826/40373589583_5a78ea37e0_m.jpg",
            imageUrl="https://farm8.staticflickr.com/7826/40373589583_5a78ea37e0_b.jpg"
        ),
        FlickrPhoto(
            title="renegade",
            date="2019-03-10T15:34:54Z",
            tags="",
            thumbUrl="https://farm8.staticflickr.com/7843/40373589673_d9156ae15c_m.jpg",
            imageUrl="https://farm8.staticflickr.com/7843/40373589673_d9156ae15c_b.jpg"
        ), FlickrPhoto(
            title="TECP5420.jpg",
            date="2019-03-10T15:34:35Z",
            tags="mrdmemphis roller derbyderby901roller derbyderbymemphis",
            thumbUrl="https://farm8.staticflickr.com/7832/46423879435_92e7f42e2b_m.jpg",
            imageUrl="https://farm8.staticflickr.com/7832/46423879435_92e7f42e2b_b.jpg"
        ),
        FlickrPhoto(
            title="Apple pie with lawyer and calvados",
            date="2019-03-10T15:34:45Z",
            tags="cookinggroupworldwide",
            thumbUrl="https://farm8.staticflickr.com/7867/46423881125_61c7d5dfeb_m.jpg",
            imageUrl="https://farm8.staticflickr.com/7867/46423881125_61c7d5dfeb_b.jpg"
        ),

        FlickrPhoto(
            title="#56 Ford Escort 1300",
            date="2019-03-10T15:34:30Z",
            tags="doningtonstagesrally leicestershire motorsport doningtonpark rally circuit",
            thumbUrl="https://farm8.staticflickr.com/7922/32396791767_da686bc2b6_m.jpg",
            imageUrl="https://farm8.staticflickr.com/7922/32396791767_da686bc2b6_b.jpg"),
        FlickrPhoto(
            title="20190310103156ch01",
            date="2019-03-10T15:34:31Z",
            tags="olden2",
            thumbUrl="https://farm8.staticflickr.com/7855/32396791797_1670cdc1ab_m.jpg",
            imageUrl="https://farm8.staticflickr.com/7855/32396791797_1670cdc1ab_b.jpg"
        ),
        FlickrPhoto(title="",
            date="2019-03-10T15:34:52Z",
            tags="",
            thumbUrl="https://farm8.staticflickr.com/7826/40373589583_5a78ea37e0_m.jpg",
            imageUrl="https://farm8.staticflickr.com/7826/40373589583_5a78ea37e0_b.jpg"
        ),
        FlickrPhoto(
            title="renegade",
            date="2019-03-10T15:34:54Z",
            tags="",
            thumbUrl="https://farm8.staticflickr.com/7843/40373589673_d9156ae15c_m.jpg",
            imageUrl="https://farm8.staticflickr.com/7843/40373589673_d9156ae15c_b.jpg"
        ), FlickrPhoto(
            title="TECP5420.jpg",
            date="2019-03-10T15:34:35Z",
            tags="mrdmemphis roller derbyderby901roller derbyderbymemphis",
            thumbUrl="https://farm8.staticflickr.com/7832/46423879435_92e7f42e2b_m.jpg",
            imageUrl="https://farm8.staticflickr.com/7832/46423879435_92e7f42e2b_b.jpg"
        ),
        FlickrPhoto(
            title="Apple pie with lawyer and calvados",
            date="2019-03-10T15:34:45Z",
            tags="cookinggroupworldwide",
            thumbUrl="https://farm8.staticflickr.com/7867/46423881125_61c7d5dfeb_m.jpg",
            imageUrl="https://farm8.staticflickr.com/7867/46423881125_61c7d5dfeb_b.jpg"
        )
    )
}