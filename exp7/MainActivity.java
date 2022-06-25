package com.example.exp7;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    Button b;
    ListView lv;
    ArrayList<HashMap<String, String>>contactList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState); setContentView(R.layout.activity_main);

        contactList = new ArrayList<>();
        lv= (ListView) findViewById(R.id.list);
        b= (Button) findViewById(R.id.fetch); b.setOnClickListener(new View.OnClickListener() {
            @Override
        public void onClick(View v) {
            String strUrl = "https://api.androidhive.info/contacts/"; new UrlHandler().execute(strUrl);
        }
        });
    }

    public class UrlHandler extends AsyncTask<String, Integer, String> {
        @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        ListAdapter adapter = new SimpleAdapter(MainActivity.this, contactList, R.layout.list_item, new String[]{ "id","name","email"},
                new int[]{R.id.cid,R.id.cname, R.id.cemail});
        lv.setAdapter(adapter);
    }

        @Override
        protected String doInBackground(String... params) {



            try {

                String json_response = null;

                URL url = new URL(params[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection(); connection.setRequestMethod("GET");
                connection.connect();
                InputStream in = new BufferedInputStream(connection.getInputStream()); json_response = convertStreamToString(in);


                if (json_response != null) {
                    try {
                        JSONObject jsonObj = new JSONObject(json_response);

// Getting JSON Array node
                        JSONArray contacts = jsonObj.getJSONArray("contacts");

// looping through All Contacts
                        for (int i = 0; i < contacts.length(); i++) {
                            JSONObject c = contacts.getJSONObject(i); String id = c.getString("id");
                            String name = c.getString("name"); String email = c.getString("email");

// tmp hash map for single contact
                            HashMap<String, String> contact = new HashMap<>();

// adding each child node to HashMap key => value
                            contact.put("id", id);
                            contact.put("name", name); contact.put("email", email);

// adding contact to contact list
                            contactList.add(contact);
                        }
                    } catch (JSONException e) {
                        Log.e("error", "Json parsing error: " + e.getMessage());
                    }
                } else {
                    Log.e("error", "Couldn't get json from server.");
                }
            } catch (MalformedURLException e) { e.printStackTrace();
            } catch (IOException e) { e.printStackTrace();
            }
            return null;
        }
        private String convertStreamToString(InputStream is) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is)); StringBuilder sb = new StringBuilder();

            String line;
            try {
                while ((line = reader.readLine()) != null) {
                    sb.append(line).append('\n');
                }
            } catch (IOException e) { e.printStackTrace();
            }
            return sb.toString();
        }
    }
}

