package com.applicationslab.ayurvedictreatment.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.applicationslab.ayurvedictreatment.R;
import com.applicationslab.ayurvedictreatment.utility.PermissionHandler;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.FindCurrentPlaceRequest;
import com.google.android.libraries.places.api.net.FindCurrentPlaceResponse;
import com.google.android.libraries.places.api.net.PlacesClient;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback{

    private static final int REQUEST_PERMISSION_ACCESS_FINE_LOCATION = 1;
    private static final int REQUEST_PERMISSION_WRITE_EXTERNAL_STORAGE = 2;
    private static final int REQUEST_PERMISSION_ACCESS_FINE_WRITE_EXTERNAL = 3;
    String PLACES_API_KEY="YOUR_PLACES_API_KEY";

    PlacesClient placesClient;
    PermissionHandler permission;

    ProgressBar progressBar;

    GoogleMap googleMap = null;
    double myLat = 0, myLon = 0;
    String myLocName = "";
    ArrayList<String> allNames = null;
    ArrayList<String> allIds = null;
    ArrayList<Double> allLats = null;
    ArrayList<Double> allLons = null;
    boolean markersAdded = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        initData();
        checkPermissions();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_PERMISSION_ACCESS_FINE_WRITE_EXTERNAL:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    initView();
                } else {
                    Toast.makeText(getApplicationContext(), "Access location and write external storage permissions are required", Toast.LENGTH_LONG).show();
                }
                break;
            case REQUEST_PERMISSION_ACCESS_FINE_LOCATION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    initView();
                } else {
                    Toast.makeText(getApplicationContext(), "Access location permission is required", Toast.LENGTH_LONG).show();
                }
                break;
            case REQUEST_PERMISSION_WRITE_EXTERNAL_STORAGE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    initView();
                } else {
                    Toast.makeText(getApplicationContext(), "Write external storage permission is required", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        if(allLats != null) {
            addMarkersToMap();
        }
    }

    private void initData() {
        permission = new PermissionHandler(this);
    }

    private void checkPermissions() {
        if(!permission.hasAccessFineLocationPermission() && !permission.hasWriteExternalStoragePermission()) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_PERMISSION_ACCESS_FINE_WRITE_EXTERNAL);
        } else if(!permission.hasAccessFineLocationPermission()) {
            //requestAccessLocationPermission();
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_PERMISSION_ACCESS_FINE_LOCATION);
        } else if(!permission.hasWriteExternalStoragePermission()) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_PERMISSION_WRITE_EXTERNAL_STORAGE);
        } else {
            initView();
        }
    }

    private void requestAccessLocationPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
            permission.showPermissionSettingsSnackbar("You need to authorize this app to access GPS location from your Settings");
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_PERMISSION_ACCESS_FINE_LOCATION);
        }
    }


    private void initView() {
        Toolbar toolBar=findViewById(R.id.toolBar);
        setSupportActionBar(toolBar);
        getSupportActionBar().setTitle("Ayurvedic Hospitals");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        progressBar = findViewById(R.id.progressBar);

        ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMapAsync(this);
        initPlacesApi();
    }

    private void initPlacesApi() {
        if (!Places.isInitialized()) {
            Places.initialize(this, PLACES_API_KEY);
        }
        placesClient = Places.createClient(this);

        List<Place.Field> fields = new ArrayList<>();
        fields.add(Place.Field.NAME);
        fields.add(Place.Field.LAT_LNG);

        FindCurrentPlaceRequest findCurrentPlaceRequest = FindCurrentPlaceRequest.newInstance(fields);
        Task<FindCurrentPlaceResponse> currentPlaceResponse = placesClient.findCurrentPlace(findCurrentPlaceRequest);

        currentPlaceResponse.addOnSuccessListener(new OnSuccessListener<FindCurrentPlaceResponse>() {
            @Override
            public void onSuccess(FindCurrentPlaceResponse findCurrentPlaceResponse) {
                myLocName = findCurrentPlaceResponse.getPlaceLikelihoods().get(0).getPlace().getName();
                LatLng currentPlaceLatLng = findCurrentPlaceResponse.getPlaceLikelihoods().get(0).getPlace().getLatLng();

                myLat = currentPlaceLatLng.latitude;
                myLon = currentPlaceLatLng.longitude;

                //myLat = 23.777176;
                //myLon = 90.399452;
                //myLocName = "Dhaka";

                String placeURL="https://maps.googleapis.com/maps/api/place/nearbysearch/json?location="
                        + myLat
                        + ","
                        + myLon
                        + "&radius=5000&types=hospital&key=" + PLACES_API_KEY;
                PlaceFinder finder=new PlaceFinder();
                finder.execute(placeURL);
            }
        });

        currentPlaceResponse.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), e.getMessage().toString(), Toast.LENGTH_LONG).show();
            }
        });

    }


    private void addMarkersToMap() {
        if(!markersAdded) {
            markersAdded = true;

            for (int i = 0; i < allLats.size(); i++) {
                LatLng markerPos = new LatLng(allLats.get(i), allLons.get(i));
                googleMap.addMarker(new MarkerOptions()
                        .position(markerPos)
                        .title("Hospital")
                        .snippet(allNames.get(i))
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
            }

            LatLng myPos = new LatLng(myLat, myLon);
            googleMap.addMarker(new MarkerOptions()
                    .position(myPos)
                    .title("You are here")
                    .snippet(myLocName)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myPos, 14));
        }
    }



    public String readConnectionString(String URL) {
        StringBuilder stringBuilder = new StringBuilder();
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(URL);
        try {
            HttpResponse response = httpClient.execute(httpGet);
            StatusLine statusLine = response.getStatusLine();
            int statusCode = statusLine.getStatusCode();
            if (statusCode == 200) {
                HttpEntity entity = response.getEntity();
                InputStream inputStream = entity.getContent();
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(inputStream));
                String line;
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line);
                }
                inputStream.close();
            } else {
                Log.d("ConnectionString", "Failed to connect");
            }
        } catch (Exception e) {
            Log.d("ConnectionString", e.getLocalizedMessage());
        }
        return stringBuilder.toString();
    }



    class PlaceFinder extends AsyncTask<String, Void, String> {

        ArrayList<String> names = new ArrayList<String>();
        ArrayList<String> ids = new ArrayList<String>();
        ArrayList<Double> lats = new ArrayList<Double>();
        ArrayList<Double> lons = new ArrayList<Double>();


        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
        }


        @Override
        protected String doInBackground(String... params) {
            // TODO Auto-generated method stub
            return readConnectionString(params[0]);
        }


        @Override
        protected void onPostExecute(String JSONString) {
            // TODO Auto-generated method stub
            try {
                JSONObject jsonObject = new JSONObject(JSONString);
                JSONArray placeItems = new JSONArray(jsonObject.getString("results"));
                for (int i = 0; i < placeItems.length(); i++) {
                    JSONObject placeItem = placeItems.getJSONObject(i);
                    Double lat = Double.parseDouble(placeItem.getJSONObject("geometry").getJSONObject("location").getString("lat"));
                    Double lon = Double.parseDouble(placeItem.getJSONObject("geometry").getJSONObject("location").getString("lng"));
                    String name = placeItem.getString("name");
                    String id = placeItem.getString("place_id");
                    names.add(name);
                    ids.add(id);
                    lats.add(lat);
                    lons.add(lon);
                }

                progressBar.setVisibility(View.GONE);

                allNames = names;
                allIds = ids;
                allLats = lats;
                allLons = lons;

                if(googleMap != null) {
                    addMarkersToMap();
                }
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }



}
