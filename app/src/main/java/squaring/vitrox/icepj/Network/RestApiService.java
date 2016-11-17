package squaring.vitrox.icepj.Network;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.os.ResultReceiver;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import squaring.vitrox.icepj.Helper.Config;
import squaring.vitrox.icepj.Model.ApiResponse;
import squaring.vitrox.icepj.Model.Movie;
import squaring.vitrox.icepj.Model.MovieDetail;
import squaring.vitrox.icepj.Model.Movies;

public class RestApiService extends IntentService {

   private static String mServiceName="RestService";
    List<Movie> mMovies;
    private static String NOMOVIES = "False";
    public RestApiService() {
        super(mServiceName);
    }
    private ResultReceiver rec;
    @Override
    protected void onHandleIntent(Intent intent) {
        String error=null;
        rec = intent.getParcelableExtra("hh");
        String movieText= intent.getStringExtra(Config.MOVIE_SERVICE_PARAM);
        URL url;
        HttpURLConnection urlConnection = null;
        ApiResponse mResponse = new ApiResponse();
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            url = new URL(Config.URL_MOVIE_LIST + movieText);

            urlConnection = (HttpURLConnection) url.openConnection();
            mResponse = mapper.readValue(urlConnection.getInputStream(), ApiResponse.class);

        } catch (Exception e) {
            error= e.getMessage();

        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        /* IF THE RESPONSE FROM SERVER IS NOT MOVIES FOUND THEN IT SEND ERROR TO VIEW */
        mMovies = mResponse.getSearch();
        String have_movies = mResponse.getResponse();
        List<MovieDetail> MovDetail = new ArrayList<>();
        if (have_movies.contains(NOMOVIES)) {
            error=Config.NO_MOVIES_ERROR;
        }else{
        /*  SEARCH FOR EACH MOVIE ON LIST THE MOVIE DETAIL */
        for (Movie mov : mMovies) {
            try {
                URL newUrl = new URL(Config.URL_MOVIE_DETAIL + mov.getImdbID());
                urlConnection = (HttpURLConnection) newUrl.openConnection();
                MovieDetail movtoadd = mapper.readValue(urlConnection.getInputStream(), MovieDetail.class);
                MovDetail.add(movtoadd);
            } catch (Exception e) {
                error= e.getMessage();
            }
        }
        }
        if (urlConnection != null) {
            urlConnection.disconnect();
        }

        if (error!=null)
        {
            Bundle b = new Bundle();
            b.putString(Config.API_SERVICE_RESPONSE, error);
            rec.send(1, b);
        }else {
            Bundle b = new Bundle();
            Movies mv = new Movies(MovDetail);
            b.putSerializable(Config.API_SERVICE_RESPONSE, mv);
            rec.send(0, b);
        }
    }
}
