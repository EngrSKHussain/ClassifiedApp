package com.skhproject.classifiedapp.ui.fragment.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.skhproject.classifiedapp.R
import com.skhproject.classifiedapp.application.ClassifiedApp
import com.skhproject.classifiedapp.databinding.HomeFragmentBinding
import com.skhproject.classifiedapp.db.entity.Listing
import com.skhproject.classifiedapp.listeners.ClickListener
import com.skhproject.classifiedapp.ui.adapter.ClassifiedListAdapter

class HomeFragment : Fragment(), ClickListener, HomeInteractions {

    private val TAG = "HomeFragment"
    private lateinit var binding: HomeFragmentBinding
    private lateinit var adapterClassified: ClassifiedListAdapter
    private val viewModel: HomeViewModel by viewModels {
        HomeViewModelFactory((activity?.application as ClassifiedApp).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.home_fragment, container, false)
        binding = DataBindingUtil.bind<HomeFragmentBinding>(view)!!
        try {
            setView()
            setObservers()
            loadData()
        } catch (e: Exception) {
            Log.d(TAG, "Exception in onCreateView: " + e.localizedMessage)
        }
        return binding.root
    }

    private fun loadData() {

        /*INFO:
        * This method loads data form API to the
        * local database
        * */

        try {
            viewModel.fetchAndSaveListingsFromServer(this);
        } catch (e: Exception) {
            Log.d(TAG, "Exception in loadData: " + e.localizedMessage)
        }
    }

    private fun setObservers() {

        /*INFO:
        * This method setups the observers
        */

        try {
            viewModel.allListing.observe(
                viewLifecycleOwner,
                Observer { t -> adapterClassified.updateContent(t) })
        } catch (e: Exception) {
            Log.d(TAG, "Exception in setObservers: " + e.localizedMessage)
        }
    }

    private fun setView() {

        /*INFO
        * This method resets the view
        * */

        try {
            adapterClassified = ClassifiedListAdapter(this)
            binding.classifiedRv.adapter = adapterClassified

            binding.pullToRefresh.setOnRefreshListener { loadData() }
        } catch (e: Exception) {
            Log.d(TAG, "Exception in setView: " + e.localizedMessage)
        }

    }

    override fun itemClick(item: Listing) {
        Toast.makeText(context, "${item.name}", Toast.LENGTH_SHORT).show()
    }

    override fun showLoading(string: String?) {
        binding.pullToRefresh.isRefreshing = true
       // Toast.makeText(context, "${string}", Toast.LENGTH_SHORT).show()
    }

    override fun dataLoaded() {
        binding.pullToRefresh.isRefreshing = false
        //Toast.makeText(context, "Data loaded", Toast.LENGTH_SHORT).show()
    }


    override fun dataLoadingFailed(string: String?) {
        binding.pullToRefresh.isRefreshing = false
        //Toast.makeText(context, "${string}", Toast.LENGTH_SHORT).show()
    }
}