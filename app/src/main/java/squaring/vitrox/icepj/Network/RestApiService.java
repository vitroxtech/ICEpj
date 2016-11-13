package squaring.vitrox.icepj.Network;

import android.app.IntentService;
import android.content.Intent;

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

/**
 * Created by miguelgomez on 11/10/16.
 */

public class RestApiService extends IntentService {

   private static String mServiceName="RestService";
    List<Movie> mMovies;
    private static String NOMOVIES = "False";

    public RestApiService() {
        super(mServiceName);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String movieText="";
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
            //mDispatcher.onError(e.getMessage());
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        /* IF THE RESPONSE FROM SERVER IS NOT MOVIES FOUND THEN IT SEND ERROR TO VIEW */
        mMovies = mResponse.getSearch();
        String have_movies = mResponse.getResponse();
        if (have_movies.contains(NOMOVIES)) {
            return null;
        }
        /*  SEARCH FOR EACH MOVIE ON LIST THE MOVIE DETAIL */
        List<MovieDetail> MovDetail = new ArrayList<>();
        for (Movie mov : mMovies) {
            try {
                URL newUrl = new URL(Config.URL_MOVIE_DETAIL + mov.getImdbID());
                urlConnection = (HttpURLConnection) newUrl.openConnection();
                MovieDetail movtoadd = mapper.readValue(urlConnection.getInputStream(), MovieDetail.class);
                MovDetail.add(movtoadd);
            } catch (Exception e) {
               // mDispatcher.onError(e.getMessage());

            }
        }
        if (urlConnection != null) {
            urlConnection.disconnect();
        }
        //return MovDetail;
    }
}
