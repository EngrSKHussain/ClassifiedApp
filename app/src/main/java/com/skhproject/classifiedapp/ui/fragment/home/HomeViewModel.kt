package com.skhproject.classifiedapp.ui.fragment.home

import androidx.lifecycle.*
import com.skhproject.classifiedapp.db.dao.NetworkResult
import com.skhproject.classifiedapp.db.entity.Listing
import com.skhproject.classifiedapp.manager.APIManager
import com.skhproject.classifiedapp.model.ClassifiedResponse
import com.skhproject.classifiedapp.repo.ListingRepo
import kotlinx.coroutines.launch


class HomeViewModel(private val repository: ListingRepo) : ViewModel() {

    val allListing: LiveData<List<Listing>> = repository.allListing.asLiveData()

    fun fetchAndSaveListingsFromServer(homeInteractions: HomeInteractions) {

        /*INFO:
        * This method uses API Manager to fetch
        * and save the data into the local db
        * */

        try {

            APIManager.fetchListings((object : NetworkResult {

                override fun loading(string: String?) {
                    homeInteractions.showLoading(string)
                }

                override fun success(classifiedResponse: Any?) {

                    viewModelScope.launch {

                        if (classifiedResponse is ClassifiedResponse) {

                            for (item in classifiedResponse.results) {

                                //Transform data into entity
                                val listing = Listing(
                                    item.uid,
                                    item.name,
                                    item.price,
                                    item.image_ids,
                                    item.image_urls,
                                    item.image_urls_thumbnails,
                                    item.created_at
                                )

                                //Save data
                                repository.insert(listing)
                            }

                            //Inform UI
                            homeInteractions.dataLoaded()

                        }
                    }

                }

                override fun failed(string: String?) {
                    homeInteractions.dataLoadingFailed(string)
                }
            }))

        } catch (e: Exception) {
            homeInteractions.dataLoadingFailed("An exception has occurred")
        }

    }

}

class HomeViewModelFactory(private val repository: ListingRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HomeViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}