package com.novalfakhri.muvi.Rest;

import com.novalfakhri.muvi.Model.MoviesResponse;
import com.novalfakhri.muvi.Model.TrailersResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Toshiba on 8/23/2017.
 */

public interface ApiInterface {
    @GET("movie/popular")
    Call<MoviesResponse> getPopularMovies(@Query("api_key") String apiKey);

    @GET("movie/upcoming")
    Call<MoviesResponse> getUpComingMovies(@Query("api_key") String apiKey);

    @GET("movie/top_rated")
    Call<MoviesResponse> getTopRatedMovies(@Query("api_key") String apiKey);

    @GET("movie/{movie_id}")
    Call<MoviesResponse> getMovieDetails(@Path("movie_id") int id, @Query("api_key") String apiKey);

    @GET("movie/{movie_id}/videos")
    Call<TrailersResponse> getMovieTrailer(@Path("movie_id") int id, @Query("api_key") String apiKey);
}
