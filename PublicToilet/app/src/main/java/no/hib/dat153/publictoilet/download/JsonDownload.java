package no.hib.dat153.publictoilet.download;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import no.hib.dat153.publictoilet.store.InternalStorage;

/**
 * Created by sindre on 21.01.15.
 */
public class JsonDownload extends AsyncTask<String, Void, String> {

    InternalStorage internalStorage;
    public final static String URL = "https://dl.dropboxusercontent.com/u/38968096/dokart.json";

    public JsonDownload(InternalStorage internalStorage){
        this.internalStorage = internalStorage;
    }

    @Override
    public String doInBackground(String... params){
        String data = "Feil URL";

        try{
            // Url til fil
            java.net.URL url = new URL(params[0]);
            // Lager en http connection og åpner url
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            // Get metode
            urlConnection.setRequestMethod("GET");
            urlConnection.setDoOutput(true);
            // Connecter til url
            urlConnection.connect();
            // Får hele filen inni en inputstream
            InputStream inputStream = urlConnection.getInputStream();

            // For å fa en string ut av inputstream
            BufferedReader r = new BufferedReader(new InputStreamReader(inputStream));
            // Gjør stringbuilder stor nok for bytes i inputstream slik at den slipper å resize
            StringBuilder total = new StringBuilder(inputStream.available());
            String line;
            while ((line = r.readLine()) != null) {
                total.append(line);
            }
            // Endleig string
            r.close();
            inputStream.close();
            data = total.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }

    @Override
    public void onPostExecute(String res){
        internalStorage.writeFile(res);
    }

}
