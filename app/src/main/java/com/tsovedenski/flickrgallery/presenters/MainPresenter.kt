package com.tsovedenski.flickrgallery.presenters

class MainPresenter : BasePresenter() {

    private var viewType = ViewType.Grid

}

enum class ViewType {
    Grid,
    Card
}