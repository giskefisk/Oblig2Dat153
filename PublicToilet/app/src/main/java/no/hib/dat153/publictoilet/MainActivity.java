package no.hib.dat153.publictoilet;


import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;


public class MainActivity extends ActionBarActivity {

    PlaceholderFragment taskFragment;
    Button btnShowLocation;
    GPSTracker gps;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnShowLocation = (Button) findViewById(R.id.show_location);
        btnShowLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gps = new GPSTracker(MainActivity.this);
                if(gps.canGetLocation()){
                    double latitude = gps.getLatitude();
                    double longitude = gps.getLongitide();
                    Toast.makeText(getApplicationContext(),"Your location is -\nLong: " + longitude + "\nLat: " +latitude,Toast.LENGTH_LONG).show();
                }else{
                    gps.showSettingsAlert();
                }
            }
        });



        if(savedInstanceState == null){
            taskFragment = new PlaceholderFragment();
            getSupportFragmentManager().beginTransaction().add(taskFragment, "MyFragment").commit();
        } else{
            taskFragment = (PlaceholderFragment) getSupportFragmentManager().findFragmentByTag("MyFragment");
        }
        taskFragment.startTask();
    }

    public void map (View view){
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW);
        intent.setData(Uri.parse("geo:"));
        startActivity(intent);
    }


    public static class PlaceholderFragment extends Fragment {
        DoKart downloadTask;
        public PlaceholderFragment(){
        }


        @Override
        public void onActivityCreated(@Nullable Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);
            setRetainInstance(true);
        }

        public void startTask(){
            if(downloadTask != null){
                downloadTask.cancel(true);
            }else {
                downloadTask = new DoKart();
                downloadTask.execute();
            }
        }

    }

    public static class DoKart extends AsyncTask<Void, Void, ArrayList<HashMap<String, String>>> {

        public DoKart(){

        }


        @Override
        protected ArrayList<HashMap<String, String>> doInBackground(Void... params) {

            String downloadURL = "http://hotell.difi.no/api/xml/bergen/dokart?";
            ArrayList<HashMap<String, String>> results = new ArrayList<>();
            try {
                URL url = new URL(downloadURL);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                InputStream inputStream = connection.getInputStream();
                results = processXML(inputStream);
            } catch (Exception e) {
                L.m(e + "");
            }
            return results;
        }

        @Override
        protected void onPostExecute(ArrayList<HashMap<String, String>> result) {
            L.m(result+"");
        }

        public ArrayList<HashMap<String, String>> processXML(InputStream inputStream) throws  Exception{
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document xmlDocument = documentBuilder.parse(inputStream);
            Element rootElement = xmlDocument.getDocumentElement();
            NodeList itemList = rootElement.getElementsByTagName("entry");
            NodeList itemChildren = null;
            Node currentItem = null;
            Node currentChild = null;
            ArrayList<HashMap<String, String>> result = new ArrayList<>();
            HashMap<String, String> currentMap = null;
            for(int i = 0; i<itemList.getLength(); i++){
                currentItem = itemList.item(i);
                itemChildren = currentItem.getChildNodes();
                currentMap = new HashMap<>();
                for(int j = 0; j < itemChildren.getLength(); j++){
                    currentChild = itemChildren.item(j);
                    if(currentChild.getNodeName().equalsIgnoreCase("latitude")){
                        currentMap.put("latitude", currentChild.getTextContent());
                    }
                    if(currentChild.getNodeName().equalsIgnoreCase("longitude")){
                        currentMap.put("longitude", currentChild.getTextContent());
                    }
                    if(currentChild.getNodeName().equalsIgnoreCase("adresse")){
                        currentMap.put("adresse", currentChild.getTextContent());
                    }
                    if(currentChild.getNodeName().equalsIgnoreCase("tid_hverdag")){

                    }
                    if (currentChild.getNodeName().equalsIgnoreCase("place")){

                    }
                }
                if(currentMap != null && !currentMap.isEmpty()){
                    result.add(currentMap);
                }
            }
            return result;
        }
    }


}