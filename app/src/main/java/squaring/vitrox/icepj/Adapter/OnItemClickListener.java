package squaring.vitrox.icepj.Adapter;

import android.graphics.Bitmap;

import squaring.vitrox.icepj.Model.MovieDetail;

public interface OnItemClickListener {
    
    void onItemClick(MovieDetail item, Bitmap bmp);
}