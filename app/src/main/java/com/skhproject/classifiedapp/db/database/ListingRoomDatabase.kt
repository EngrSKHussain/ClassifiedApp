package com.skhproject.classifiedapp.db.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.skhproject.classifiedapp.db.common.Converters
import com.skhproject.classifiedapp.db.dao.ListingDao
import com.skhproject.classifiedapp.db.entity.Listing
import kotlinx.coroutines.CoroutineScope

@Database(entities = [Listing::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
public abstract class ListingRoomDatabase : RoomDatabase() {

    abstract fun listingDao(): ListingDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time. 
        @Volatile
        private var INSTANCE: ListingRoomDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): ListingRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ListingRoomDatabase::class.java,
                    "word_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}