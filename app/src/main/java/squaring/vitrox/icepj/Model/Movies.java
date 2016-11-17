package squaring.vitrox.icepj.Model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by miguelgomez on 11/16/16.
 */

public class Movies implements Serializable {

    private List<MovieDetail> movieDetailList;

    public Movies(List<MovieDetail> myMovies)
    {
        this.movieDetailList = myMovies;
    }

    public List<MovieDetail> getMovieDetailList() {
        return movieDetailList;
    }

}
