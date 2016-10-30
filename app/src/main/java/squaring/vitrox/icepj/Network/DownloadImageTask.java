package squaring.vitrox.icepj.Network;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {

    DownloadImageInterface dispatcher;

    public void addListener(DownloadImageInterface toAdd) {
        dispatcher = toAdd;
    }

    @Override
    protected Bitmap doInBackground(String... URL) {
        String imageURL = URL[0];
        Bitmap bitmap = null;
        try {
            /*  DOWNLOAD IMAGE URL */
            HttpURLConnection urlConnection = null;
            java.net.URL url = new URL(imageURL);
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream input = urlConnection.getInputStream();
            /*  DECODE BITMAP*/
            bitmap = BitmapFactory.decodeStream(input);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap result) {
        dispatcher.ImageDownloaded(result);

    }


}