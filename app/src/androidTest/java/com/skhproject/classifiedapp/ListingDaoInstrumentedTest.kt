package com.skhproject.classifiedapp

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.skhproject.classifiedapp.db.dao.ListingDao
import com.skhproject.classifiedapp.db.database.ListingRoomDatabase
import com.skhproject.classifiedapp.db.entity.Listing
import kotlinx.coroutines.runBlocking
import org.junit.After

import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.io.IOException


@RunWith(AndroidJUnit4::class)
class ListingDaoInstrumentedTest {

    private lateinit var listingDao: ListingDao
    private lateinit var db: ListingRoomDatabase

    @Before
    fun createDb() {
        val context: Context = ApplicationProvider.getApplicationContext()
        // Using an in-memory database because the information stored here disappears when the
        // process is killed.
        db = Room.inMemoryDatabaseBuilder(context, ListingRoomDatabase::class.java)
            // Allowing main thread queries, just for testing.
            .allowMainThreadQueries()
            .build()

        listingDao = db.listingDao()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetWord() = runBlocking {


        val validateListingItem = Listing(
            "4878bf592579410fba52941d00b62f94",
            "Notebook",
            "AED 5",
            arrayListOf("9355183956e3445e89735d877b798689"),
            arrayListOf(
                "https://demo-app-photos-45687895456123.s3.amazonaws.com/9355183956e3445e89735d877b798689?AWSAccessKeyId=ASIASV3YI6A4UWFWGMU7&Signature=rFo7XCtEE7yKgjFhQ4BxoEwxEXA%3D&x-amz-security-token=IQoJb3JpZ2luX2VjELT%2F%2F%2F%2F%2F%2F%2F%2F%2F%2FwEaCXVzLWVhc3QtMSJHMEUCIAOJO%2BoTKkafiKAADJu%2Bw5xr1ExcL3MGXhVIRFU1soePAiEA0klQJ38R6hJtkTO7EWVm%2B1tu7YZmJcy76WCpPfJPLdgqlAIIfBADGgwxODQzOTg5NjY4NDEiDKdz3EwRtR4abVTChSrxAS5XoUgem8CCUxZAJ5jcigI4uxKvNkc%2Fw91QAS%2FTtEO2C1R58OOj5q0XgEImeda8TbHkaN8YvpQzMBMXjyzlyqd9ZUn6TnVOHVYOuutGpdRU1Z4NmczPFluk0%2BjGsx4cgKUnrDubIvFruYyLcZkflmiK9Zgfk52dqiD%2BxkErvkzWMrjSbhTVjyZ9pCQwIcdz8QTB7D2NDsoVWKFYljV989lHlTffT%2FCU7t%2BkjDkKk6zAIwxBrPYVz0F3JQBQNdE521aVwsuueeRFvpP2ZnQ8XupcP6ZnCusscKxy7XI3ZC0h2nIwVL3ULx4yyimBCcFMHnkwp77%2FjAY6mgFVzEbqD1jQJnX6G6euiPbWPbJrt46czNavWNEzhjk3TOjgifqaYnZ7Q1kG%2Feh0Dgeuiq%2FrFWZceXuQJKECIXLAPLb5zpd92gQPKxvGauQZ%2B6rOH53NH0fWFC5r18YzPpkh1eRC2rTgX0QgcOlJ%2FRkOGLFOfW7q2eGxlCLkudPcjqnbxUFFXR1jotXNGob94AMjKO%2F04ahKnK%2BE&Expires=1637871477"
            ),
            arrayListOf(
                "https://demo-app-photos-45687895456123.s3.amazonaws.com/9355183956e3445e89735d877b798689-thumbnail?AWSAccessKeyId=ASIASV3YI6A4UWFWGMU7&Signature=fSymcbgZUcXjegpc9aNGT1DdLi8%3D&x-amz-security-token=IQoJb3JpZ2luX2VjELT%2F%2F%2F%2F%2F%2F%2F%2F%2F%2FwEaCXVzLWVhc3QtMSJHMEUCIAOJO%2BoTKkafiKAADJu%2Bw5xr1ExcL3MGXhVIRFU1soePAiEA0klQJ38R6hJtkTO7EWVm%2B1tu7YZmJcy76WCpPfJPLdgqlAIIfBADGgwxODQzOTg5NjY4NDEiDKdz3EwRtR4abVTChSrxAS5XoUgem8CCUxZAJ5jcigI4uxKvNkc%2Fw91QAS%2FTtEO2C1R58OOj5q0XgEImeda8TbHkaN8YvpQzMBMXjyzlyqd9ZUn6TnVOHVYOuutGpdRU1Z4NmczPFluk0%2BjGsx4cgKUnrDubIvFruYyLcZkflmiK9Zgfk52dqiD%2BxkErvkzWMrjSbhTVjyZ9pCQwIcdz8QTB7D2NDsoVWKFYljV989lHlTffT%2FCU7t%2BkjDkKk6zAIwxBrPYVz0F3JQBQNdE521aVwsuueeRFvpP2ZnQ8XupcP6ZnCusscKxy7XI3ZC0h2nIwVL3ULx4yyimBCcFMHnkwp77%2FjAY6mgFVzEbqD1jQJnX6G6euiPbWPbJrt46czNavWNEzhjk3TOjgifqaYnZ7Q1kG%2Feh0Dgeuiq%2FrFWZceXuQJKECIXLAPLb5zpd92gQPKxvGauQZ%2B6rOH53NH0fWFC5r18YzPpkh1eRC2rTgX0QgcOlJ%2FRkOGLFOfW7q2eGxlCLkudPcjqnbxUFFXR1jotXNGob94AMjKO%2F04ahKnK%2BE&Expires=1637871477"
            ),
            "2019-02-24 04:04:17.566515"
        )

        listingDao.insert(validateListingItem)
        val allListing = listingDao.getListingByPrice().first()
        assertEquals(allListing.uid, validateListingItem.uid)
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }
}
