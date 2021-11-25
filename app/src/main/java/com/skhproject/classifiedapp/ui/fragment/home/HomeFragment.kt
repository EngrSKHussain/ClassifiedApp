package com.skhproject.classifiedapp.ui.fragment.home

import android.os.Bundle
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

class HomeFragment : Fragment(), ClickListener {

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
        setView()
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.allListing.observe(viewLifecycleOwner, Observer { t -> adapterClassified.updateContent(t) })
        viewModel.getClassifiedFromServer();
        // TODO: Use the ViewModel
    }




    private fun setView() {

        adapterClassified = ClassifiedListAdapter(this)
        binding.classifiedRv.adapter = adapterClassified

    }

    override fun itemClick(item: Listing) {
        //        adapter.itemClickSubject.subscribe {
//            findNavController().navigate(R.id.action_home_fragment_to_detail_fragment)
//        }

        Toast.makeText(context, "${item.name}", Toast.LENGTH_SHORT).show()
    }

}