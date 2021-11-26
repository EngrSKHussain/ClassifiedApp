package com.skhproject.classifiedapp.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.skhproject.classifiedapp.db.entity.Listing
import kotlinx.coroutines.flow.Flow

@Dao
interface ListingDao {

    @Query("SELECT * FROM listing ORDER BY price ASC")
    fun getListingByPrice(): List<Listing>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(word: Listing)

    @Query("DELETE FROM listing")
    suspend fun deleteAll()

    @Query("SELECT * FROM listing ORDER BY created_at DESC")
    fun getAllListingFlow(): Flow<List<Listing>>

    @Query("SELECT * FROM listing where uid = :uid")
    fun getListingByIdLD(uid: String): LiveData<Listing>
}