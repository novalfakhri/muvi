package com.novalfakhri.muvi;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.novalfakhri.muvi.Adapter.MoviesAdapter;
import com.novalfakhri.muvi.Model.Movies;
import com.novalfakhri.muvi.Model.MoviesResponse;
import com.novalfakhri.muvi.Rest.ApiClient;
import com.novalfakhri.muvi.Rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpComingFragment extends Fragment {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    private static final String TAG = MainActivity.class.getSimpleName();
    private final static String API_KEY = "INSERT TMDB API HERE";


    public UpComingFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);

        layoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(layoutManager);

        LoadData();
        return rootView;
    }

    private void LoadData() {

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<MoviesResponse> call = apiService.getUpComingMovies(API_KEY);

        call.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse>call, Response<MoviesResponse> response) {
                final List<Movies> movies = response.body().getResults();

                recyclerView.setAdapter(new MoviesAdapter(movies, R.layout.item_card, getContext()));
            }

            @Override
            public void onFailure(Call<MoviesResponse>call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });
    }
}
