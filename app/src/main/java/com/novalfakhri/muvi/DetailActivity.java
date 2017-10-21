package com.novalfakhri.muvi;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;
import com.novalfakhri.muvi.Model.TrailersResponse;
import com.novalfakhri.muvi.Rest.ApiClient;
import com.novalfakhri.muvi.Rest.ApiInterface;

import org.json.JSONArray;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DetailActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {
    TextView title, release, rating, plot;
    ImageView backdrop, watchlist, favorite;
    YouTubePlayerFragment playerFragment;
    JSONObject jsonObject;
    JSONArray jsonArray;
    String keyTrailer;

    private final static String YOUTUBE_API_KEY = "INSERT YOUTUBE API HERE";
    private final static String API_KEY = "INSERT TMDB API HERE";
    private final static int RECOVERY_REQUEST = 1;
    private static final String TAG = DetailActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        title = (TextView) findViewById(R.id.detail_title);
        release = (TextView) findViewById(R.id.detail_release);
        rating = (TextView) findViewById(R.id.detail_rating);
        plot = (TextView) findViewById(R.id.detail_plot);
//        backdrop = (ImageView) findViewById(R.id.detail_backdrop);


        playerFragment =  (YouTubePlayerFragment) getFragmentManager().findFragmentById(R.id.youtube_player);
        playerFragment.initialize(YOUTUBE_API_KEY, this);

        Bundle bundle = getIntent().getExtras();
        title.setText(bundle.getString("title"));
        release.setText(bundle.getString("release"));
        rating.setText(bundle.getString("rating"));
        plot.setText(bundle.getString("plot"));
//        Glide.with(DetailActivity.this)
//                .load("https://image.tmdb.org/t/p/w780" + bundle.getString("backdrop"))
//                .into(backdrop);

        int id = bundle.getInt("id");

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<TrailersResponse> call = apiService.getMovieTrailer(id,API_KEY);

        call.enqueue(new Callback<TrailersResponse>() {

            @Override
            public void onResponse(Call<TrailersResponse>call, Response<TrailersResponse> response) {
                final String key = response.body().getResults().get(0).getKey();
                keyTrailer = key;

            }
            @Override
            public void onFailure(Call<TrailersResponse>call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean b) {
        if (!b) {
            player.cueVideo(keyTrailer);
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult result) {
        if(result.isUserRecoverableError()){
            result.getErrorDialog(this, RECOVERY_REQUEST).show();
        }else{
            Toast.makeText(this, "YouTubePlayer.onInitializationFailure(): " + result.toString(),
                    Toast.LENGTH_LONG).show();
        }
    }
}
