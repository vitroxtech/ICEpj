package squaring.vitrox.icepj.Network;

import java.util.List;

import squaring.vitrox.icepj.Model.MovieDetail;

/**
 * Created by miguelgomez on 10/28/16.
 */

public interface RestApiInterface {

    void onListLoaded(List<MovieDetail> movies);

    void onError(String error);
}
