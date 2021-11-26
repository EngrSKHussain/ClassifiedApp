package com.skhproject.classifiedapp.manager

import android.util.Log
import com.skhproject.classifiedapp.db.dao.NetworkResult
import com.skhproject.classifiedapp.model.ClassifiedResponse
import com.skhproject.classifiedapp.network.RestService
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.SocketTimeoutException
import java.net.UnknownHostException

object APIManager {

    val TAG = "APIManager"

    fun fetchListings(networkResult: NetworkResult) {

        /*INFO:
        * This method will enqueue getListing method
        * and fetch the result and give a callback as
        * network result
        *  */


        try {

            networkResult.loading("Fetching data from server")

            val listingService = RestService.listingService.getListing()

            listingService.enqueue(object : Callback<ClassifiedResponse> {
                override fun onResponse(
                    call: Call<ClassifiedResponse>?,
                    response: Response<ClassifiedResponse>?
                ) {

                    //Check for network and API errors
                    val errorMsg = isResponseValid(response)

                    if (errorMsg != null) {
                        networkResult.failed(errorMsg)
                    } else {
                        networkResult.success(response?.body())
                    }


                }

                override fun onFailure(call: Call<ClassifiedResponse>?, t: Throwable?) {

                    //Handle failure cases
                    var errorMsg: String? = "Request Failed, please try again later"

                    try {
                        errorMsg = if (t is SocketTimeoutException || t is UnknownHostException) {
                            "Request timeout, please try again later"
                        } else {
                            t?.localizedMessage
                        }
                        networkResult.failed(errorMsg)
                    } catch (e: Exception) {
                    }

                }
            })

        } catch (e: Exception) {
            networkResult.failed("An exception has occurred")
            Log.e(TAG, "Exception in fetchListings: " + e.localizedMessage)
        }

    }

    fun isResponseValid(response: Response<*>?): String? {

        /*INFO:
         * This method checks if a given retrofit response is
         * ready to consume or has error
         * if has error it returns the error
         * msg
         * */

        try {

            if (response == null) {
                //Might be timeout case
                return "Improper response form server"
            }
            if (response.body() == null && response.errorBody() == null) {
                //Improper response case
                return "Improper response form server"
            }
            if (response.errorBody() != null) {

                //Error Case
                var errorMsg: String? = getMsgFromErrorResponseBody(response.errorBody())

                if (errorMsg == null) {
                    errorMsg = "Unknown server error has occurred"
                }

                return errorMsg
            }

            if (response.body() == null) {
                return "Improper response form server"
            }

        } catch (e: Exception) {
            Log.e(TAG, "Exception in isResponseValid: " + e.localizedMessage)
        }

        return null
    }

    private fun getMsgFromErrorResponseBody(responseBody: ResponseBody): String? {

        /*INFO:
         * This method gets the error msg
         * from retrofit errorBody
         * */

        try {

            var errorString: String? = null
            try {
                errorString = responseBody.string()
            } catch (e: Exception) {
            }
            if (errorString == null) {
                return null
            }

            return errorString;

        } catch (e: Exception) {
            Log.e(TAG, "Exception in getMsgFromErrorResponseBody: " + e.localizedMessage)
        }

        return null
    }


}