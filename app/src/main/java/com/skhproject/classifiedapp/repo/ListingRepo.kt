package com.skhproject.classifiedapp.repo

import androidx.annotation.WorkerThread
import com.skhproject.classifiedapp.db.dao.ListingDao
import com.skhproject.classifiedapp.db.entity.Listing
import kotlinx.coroutines.flow.Flow

// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
class ListingRepo(private val listingDao: ListingDao) {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val allListing: Flow<List<Listing>> = listingDao.getClassifiedAddsFlow()
    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(add: Listing) {
        listingDao.insert(add)
    }
}