package no.hib.dat153.publictoilet.download;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;

/**
 * Created by sindre on 20.01.15.
 */
public class ImageDownload extends AsyncTask<String, Void, Bitmap> {

    // Felt variabel
    ImageView imageView;

    // Konstruktør
    public ImageDownload(ImageView imageView) {
        this.imageView = imageView;
    }


    // Async metode
    @Override
    protected Bitmap doInBackground(String... urls) {
        String urldisplay = urls[0];
        Bitmap bitmap = null;
        try {
            InputStream in = new java.net.URL(urldisplay).openStream();
            bitmap = BitmapFactory.decodeStream(in);
            in.close();
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return bitmap;
    }

    // Setter view
    @Override
    protected void onPostExecute(Bitmap result) {
        imageView.setImageBitmap(result);
    }




}
