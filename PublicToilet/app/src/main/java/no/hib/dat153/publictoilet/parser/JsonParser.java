package no.hib.dat153.publictoilet.parser;

import android.annotation.TargetApi;
import android.os.Build;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import no.hib.dat153.publictoilet.MainActivity;
import no.hib.dat153.publictoilet.modell.Entry;

/**
 * Created by sindre on 21.01.15.
 */
public class JsonParser {
    String jString;
    JSONArray json;

    public JsonParser(String jString) throws JSONException {
        this.jString = jString;
        JSONObject kast = new JSONObject(jString);
        json = kast.getJSONArray("entries");
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public ArrayList<Entry> parseToEntry(){
        ArrayList<Entry> list = new ArrayList<Entry>();

        Entry temp = null;


        try {
            for(int i = 0; i< json.length(); i++){
                temp = new Entry();
                temp.setPissoir_only(json.getJSONObject(i).get("pissoir_only").toString());
                temp.setAdresse(json.getJSONObject(i).get("adresse").toString());
                temp.setRullestol(json.getJSONObject(i).get("rullestol").toString());
                temp.setPlassering(json.getJSONObject(i).get("plassering").toString());
                temp.setStellerom(json.getJSONObject(i).get("stellerom").toString());
                temp.setPris(json.getJSONObject(i).get("pris").toString());
                temp.setLongitude(json.getJSONObject(i).get("longitude").toString());
                temp.setLatitude(json.getJSONObject(i).get("latitude").toString());
                temp.setPlace(json.getJSONObject(i).get("place").toString());


                list.add(temp);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return list;
    }


}
