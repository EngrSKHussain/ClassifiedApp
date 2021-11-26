package com.skhproject.classifiedapp.ui.fragment.detail;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavHostController;
import androidx.navigation.Navigation;

import com.skhproject.classifiedapp.R;
import com.skhproject.classifiedapp.databinding.DetailFragmentBinding;
import com.skhproject.classifiedapp.db.entity.Listing;
import com.squareup.picasso.Picasso;
import com.synnapps.carouselview.ImageListener;

import org.jetbrains.annotations.NotNull;

import okhttp3.internal.Util;


public class DetailFragment extends Fragment {

    private final String TAG = "DetailFragment";
    private DetailViewModel detailViewModel;
    private DetailFragmentBinding binding;
    public static String ARG_UID = "uid";
    private String uid;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        detailViewModel = new ViewModelProvider(this).get(DetailViewModel.class);

        binding = DetailFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        setupListeners();

        unPackBundle();

        setUpObservers();

        return root;
    }

    private void setupListeners() {

        /*INFO:
         * This method will setup listeners
         * */

        try {

            binding.backButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Navigation.findNavController(v).popBackStack();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void unPackBundle() {

        /*INFO:
         * This method will retrieve all the data form the
         * bundle
         * */

        try {

            Bundle arguments = getArguments();
            if (arguments != null) {
                uid = arguments.getString(ARG_UID);
            }

            detailViewModel.loadData(uid);

        } catch (Exception e) {
            Log.e(TAG, "Exception in unPackBundle: " + e.getLocalizedMessage());
        }
    }

    private void setUpObservers() {

        /*INFO:
         * This method will setup observers
         * */

        try {
            detailViewModel.getListLiveData().observe(getViewLifecycleOwner(), this::setupViews);
        } catch (Exception e) {
            Log.e(TAG, "Exception in setUpObservers: " + e.getLocalizedMessage());
        }

    }

    private void setupViews(Listing listing) {

        /*INFO:
         * This method will setups views
         * for the listing
         * */

        try {
            binding.carouselView.setImageListener(new ImageListener() {
                @Override
                public void setImageForPosition(int i, ImageView imageView) {
                    Picasso.get().load(listing.getImage_urls().get(i)).into(imageView);
                }
            });
            binding.carouselView.setPageCount(listing.getImage_urls().size());

            binding.name.setText(listing.getName());
            binding.price.setText(listing.getPrice());
            binding.date.setText(listing.getCreated_at().substring(0, 16));
        } catch (Exception e) {
            Log.e(TAG, "Exception in setupViews: " + e.getLocalizedMessage());
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}