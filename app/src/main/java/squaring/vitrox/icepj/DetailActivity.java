package squaring.vitrox.icepj;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import squaring.vitrox.icepj.Helper.Config;
import squaring.vitrox.icepj.Model.MovieDetail;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent i = getIntent();
        Bundle mov = i.getExtras();

        MovieDetail detail = (MovieDetail) mov.getSerializable(Config.BUNDLE_MOVIE);
        Bitmap bmp = BitmapFactory.decodeByteArray(mov.getByteArray(Config.BUNDLE_IMAGE), 0, mov.getByteArray(Config.BUNDLE_IMAGE).length);

        ImageView imageView = (ImageView) findViewById(R.id.main_backdrop);
        imageView.setImageBitmap(bmp);

        ((TextView) findViewById(R.id.movie_title)).setText(detail.getTitle());
        ((TextView) findViewById(R.id.movie_writers)).setText(detail.getWriter());
        ((TextView) findViewById(R.id.movie_actors)).setText(detail.getActors());
        ((TextView) findViewById(R.id.movie_director)).setText(detail.getDirector());
        ((TextView) findViewById(R.id.movie_genre)).setText(detail.getGenre());
        ((TextView) findViewById(R.id.movie_released)).setText(detail.getReleased());
        ((TextView) findViewById(R.id.movie_plot)).setText(detail.getPlot());
        ((TextView) findViewById(R.id.movie_duration)).setText(detail.getRuntime());
    }
}
