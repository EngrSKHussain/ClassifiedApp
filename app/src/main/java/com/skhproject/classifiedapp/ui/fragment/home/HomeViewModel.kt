package com.skhproject.classifiedapp.ui.fragment.home

import android.util.Log
import androidx.lifecycle.*
import com.skhproject.classifiedapp.db.entity.Listing
import com.skhproject.classifiedapp.model.ClassifiedResponse
import com.skhproject.classifiedapp.network.RestService
import com.skhproject.classifiedapp.repo.ListingRepo
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeViewModel(private val repository: ListingRepo) : ViewModel() {

    // Using LiveData and caching what allWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    val allListing: LiveData<List<Listing>> = repository.allListing.asLiveData()
    val TAG = "HomeViewModel"

    // TODO: Implement the ViewModel
    public fun getClassifiedFromServer() {

        val listingService = RestService.listingService.getClassified()

        listingService.enqueue(object : Callback<ClassifiedResponse> {
            override fun onResponse(
                call: Call<ClassifiedResponse>?,
                response: Response<ClassifiedResponse>?
            ) {

                if (response?.body() != null) {
                    viewModelScope.launch {

                        Log.d(TAG, "onResponse: ")

                        //TODO: Validate and save the listings
                        for (item in response?.body().results) {

                            //listingItem
                            val listing = Listing(
                                item.uid,
                                item.name,
                                item.price,
                                item.image_ids,
                                item.image_urls,
                                item.image_urls_thumbnails,
                                item.created_at
                            )

                            repository.insert(listing)
                        }

                    }
                }

            }

            override fun onFailure(call: Call<ClassifiedResponse>?, t: Throwable?) {

            }
        })

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