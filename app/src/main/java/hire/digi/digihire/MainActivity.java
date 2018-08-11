package hire.digi.digihire;

import android.app.Activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import org.json.JSONObject;

import android.view.View;
import android.widget.ListView;
import android.widget.Button;
import java.util.List;
import java.util.ArrayList;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.io.InputStreamReader;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;


import android.widget.ArrayAdapter;
import java.net.URL;

public class MainActivity extends Activity {
String baseUrl="https://www.digihire.in/beta2/api/viewQuestionDetails";
    List<String>  globalitems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.interview_questions);

        Button idbtn= (Button) findViewById(R.id.btnList);
        idbtn.setOnClickListener(  new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
                // set a LinearLayoutManager with default vertical orientation
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(linearLayoutManager);
                //  call the constructor of CustomAdapter to send the reference and data to Adapter
                CustomAdapter customAdapter = new CustomAdapter(MainActivity.this, new ArrayList<String>(globalitems));
                recyclerView.setAdapter(customAdapter); // set the Adapter to RecyclerView
                //DisplayList();
            }
        });
        sendPost();
    }

    public void sendPost() {
       Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL("https://www.digihire.in/beta2/api/viewQuestionDetails");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                    conn.setRequestProperty("Accept","application/json");
                    conn.setDoOutput(true);
                    conn.setDoInput(true);

                    JSONObject jsonParam = new JSONObject();
                    jsonParam.put("ttName", "Ravindra  Tiwari");
                    jsonParam.put("jobId", "185");
                    jsonParam.put("ttId", "298");
                    jsonParam.put("tc_id", "6");

                    Log.i("JSON", jsonParam.toString());
                    DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                    //os.writeBytes(URLEncoder.encode(jsonParam.toString(), "UTF-8"));
                    os.writeBytes(jsonParam.toString());

                    os.flush();
                    os.close();

                    Log.i("STATUS", String.valueOf(conn.getResponseCode()));
                    Log.i("MSG" , conn.getResponseMessage());

                    if(conn.getResponseCode() == HttpURLConnection.HTTP_OK){
                        String responseString = readStream(conn.getInputStream());
                        Log.v("CatalogClient-Response", responseString);

                        populateList(responseString);
                    }else{
                        Log.v("CatalogClient", "Response code:"+ conn.getResponseCode());
                        Log.v("CatalogClient", "Response message:"+ conn.getResponseMessage());
                    }

                    conn.disconnect();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
//
      thread.start();
    }

    private void populateList(String json_string) {

        try {


           globalitems = new ArrayList<>();
            JSONObject root = new JSONObject(json_string);
            JSONObject root1 = root.getJSONObject("qDetails");
            JSONArray array = root1.getJSONArray("qDetails");
Log.d("Array",array.toString());
             JSONArray list= new JSONArray(array.getString(0));
            for (int i = 0; i < list.length(); i++) {
                Log.d("Array next",list.getString(i));
                //JSONObject object = array.getJSONObject(i);
              globalitems.add(list.getString(i));
            }




        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void  DisplayList()
    {
        ListView listView = (ListView) findViewById(R.id.questionslist);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, globalitems);

        if (listView != null) {
            listView.setAdapter(adapter);
        }
    }
    private String readStream(InputStream in) {
        BufferedReader reader = null;
        StringBuffer response = new StringBuffer();
        try {
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return response.toString();
    }
}
