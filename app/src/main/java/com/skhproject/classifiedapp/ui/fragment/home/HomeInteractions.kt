package com.skhproject.classifiedapp.ui.fragment.home

interface HomeInteractions {
    fun showLoading(string: String?)
    fun dataLoaded()
    fun dataLoadingFailed(string: String?)
}