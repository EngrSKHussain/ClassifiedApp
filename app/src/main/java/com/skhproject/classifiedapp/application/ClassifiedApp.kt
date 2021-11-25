package com.skhproject.classifiedapp.application

import android.app.Application
import com.skhproject.classifiedapp.db.database.ListingRoomDatabase
import com.skhproject.classifiedapp.repo.ListingRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class ClassifiedApp : Application() {

    // No need to cancel this scope as it'll be torn down with the process
    val applicationScope = CoroutineScope(SupervisorJob())

    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    val database by lazy { ListingRoomDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { ListingRepo(database.listingDao()) }
}