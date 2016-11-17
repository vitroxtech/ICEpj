package squaring.vitrox.icepj;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import java.io.ByteArrayOutputStream;

import squaring.vitrox.icepj.Adapter.MoviesAdapter;
import squaring.vitrox.icepj.Adapter.OnItemClickListener;
import squaring.vitrox.icepj.Helper.Config;
import squaring.vitrox.icepj.Helper.RestApiReceiver;
import squaring.vitrox.icepj.Model.MovieDetail;
import squaring.vitrox.icepj.Model.Movies;
import squaring.vitrox.icepj.Network.RestApiService;


public class MainActivity extends AppCompatActivity implements RestApiReceiver.Receiver, OnItemClickListener {

    private RecyclerView mMovieListRecyclerView;
    private MoviesAdapter mMovieAdapter;
    private Button mSearchB;
    private EditText mSearchText;
    private ProgressBar mProgress;
    RestApiReceiver mReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mReceiver= new RestApiReceiver(new Handler());
        mReceiver.setReceiver(this);

        mProgress = (ProgressBar) findViewById(R.id.spinner);
        mSearchB = (Button) findViewById(R.id.search_button);
        mSearchB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search();
            }
        });
        mSearchText = (EditText) findViewById(R.id.search_edittext);
        mMovieListRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mMovieAdapter = new MoviesAdapter(this, MainActivity.this);
        mMovieListRecyclerView.setAdapter(mMovieAdapter);
        GridLayoutManager gridLayoutManager =
                new GridLayoutManager(this, getResources().getInteger(R.integer.grid_column_count));
        mMovieListRecyclerView.setLayoutManager(gridLayoutManager);
    }

    @Override
    public void onItemClick(MovieDetail item, Bitmap bmp) {
        ByteArrayOutputStream bStream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, bStream);
        byte[] byteArray = bStream.toByteArray();
        Intent i = new Intent(this, DetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(Config.BUNDLE_MOVIE, item);
        bundle.putByteArray(Config.BUNDLE_IMAGE, byteArray);
        i.putExtras(bundle);
        startActivity(i);
    }

    public void search() {
        hideKeyboard();
        mProgress.setVisibility(View.VISIBLE);
        mMovieListRecyclerView.setVisibility(View.GONE);
        Intent i = new Intent(this, RestApiService.class);
        i.putExtra(Config.MOVIE_SERVICE_PARAM, mSearchText.getText().toString());
        i.putExtra("hh", mReceiver);
        startService(i);
    }

    private void hideKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager) this.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
    }

    @Override
    public void onReceiveResult(int resultCode, Bundle resultData) {

        if (resultCode == Config.ERROR_CODE) {
            String error = resultData.getString(Config.API_SERVICE_RESPONSE);
            mProgress.setVisibility(View.GONE);
            Snackbar.make(findViewById(android.R.id.content), error, Snackbar.LENGTH_SHORT).show();
        }else {
            Movies respons = (Movies) resultData.getSerializable(Config.API_SERVICE_RESPONSE);
            mMovieAdapter.addData(respons.getMovieDetailList());
            mProgress.setVisibility(View.GONE);
            mMovieListRecyclerView.setVisibility(View.VISIBLE);
        }
    }
}
