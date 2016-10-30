package squaring.vitrox.icepj.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import squaring.vitrox.icepj.Helper.Config;
import squaring.vitrox.icepj.Model.MovieDetail;
import squaring.vitrox.icepj.Network.DownloadImageInterface;
import squaring.vitrox.icepj.Network.DownloadImageTask;
import squaring.vitrox.icepj.R;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> {

    private List<MovieDetail> mDataSet;
    private Context mContext;
    private final OnItemClickListener mlistener;

    public MoviesAdapter(Context context, OnItemClickListener listener) {
        mContext = context;
        mlistener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements DownloadImageInterface {
        private View mView;
        private TextView mTitleView;
        private ImageView mThumbImageView;
        private TextView mYearView;
        private TextView mDirectorView;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mThumbImageView = (ImageView) view.findViewById(R.id.thumbnail);
            mDirectorView = (TextView) view.findViewById(R.id.movie_director);
            mTitleView = (TextView) view.findViewById(R.id.movie_title);
            mYearView = (TextView) view.findViewById(R.id.movie_year);
        }

        public void bind(final MovieDetail movieDetail) {
            mDirectorView.setText(movieDetail.getDirector());
            mTitleView.setText(movieDetail.getTitle());
            mYearView.setText(movieDetail.getYear());

            final String imageUrl;
            /*  IF MOVIE HAVE NOT POSTER AVAILABLE THEN WE SET A DEFAULT IMAGE OF N/A (url in config file)*/
            if (!movieDetail.getPoster().contains("N/A")) {
                imageUrl = movieDetail.getPoster();
            } else {
                imageUrl = Config.IMAGE_NOT_AVAILABLE;
            }
            DownloadImageTask dw = new DownloadImageTask();
            dw.addListener(this);
            dw.execute(imageUrl);
            mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Bitmap bitmap = ((BitmapDrawable) mThumbImageView.getDrawable()).getBitmap();
                    mlistener.onItemClick(movieDetail, bitmap);
                }
            });
        }

        @Override
        public void ImageDownloaded(Bitmap bmp) {
            mThumbImageView.setImageBitmap(bmp);
        }
    }


    public void addData(List<MovieDetail> data) {
        mDataSet = data;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.bind(mDataSet.get(position));
    }

    @Override
    public int getItemCount() {
        if (mDataSet == null) {
            return 0;
        }
        return mDataSet.size();
    }
}