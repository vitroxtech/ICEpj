package squaring.vitrox.icepj.Network;

import android.os.AsyncTask;

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

public class RestApiClient extends AsyncTask<String, Void, List<MovieDetail>> {

    RestApiInterface mDispatcher;
    List<Movie> mMovies;
    private static String NOMOVIES = "False";

    public void addListener(RestApiInterface toAdd) {
        mDispatcher = toAdd;
    }

    @Override
    protected List<MovieDetail> doInBackground(String... params) {

        String movieText = params[0];
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
            mDispatcher.onError(e.getMessage());
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
                mDispatcher.onError(e.getMessage());

            }
        }
        if (urlConnection != null) {
            urlConnection.disconnect();
        }
        return MovDetail;
    }

    @Override
    protected void onPostExecute(List<MovieDetail> result) {
        super.onPostExecute(result);
        if (result == null) {
            mDispatcher.onError(Config.NO_MOVIES_ERROR);
        } else {
            mDispatcher.onListLoaded(result);
        }
    }


}