package hire.digi.digihire;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;



public class EducationActivity extends AppCompatActivity {
    ArrayList<Degree> globalitems;
    ArrayList<Area> globalAreas;
    ArrayList<Qualification> globalQualifications;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_education);
        Button btn = findViewById(R.id.btnAdd);

        btn.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View view) {
                                       buttonClick();
                                   }
                               }
        );
        sendPost();

         }
    public void sendPost() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL("https://www.digihire.in/beta2/api/getMasterCandidateDetails");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                    conn.setRequestProperty("Accept","application/json");
                    conn.setDoOutput(true);
                    conn.setDoInput(true);

                    DataOutputStream os = new DataOutputStream(conn.getOutputStream());

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
    private void populateList(String json_string) {

        try {


            globalitems = new ArrayList<>();
            JSONObject root = new JSONObject(json_string);
            JSONArray array = root.getJSONArray("degree");
            Log.d("Array",array.toString());
            for (int i = 0; i < array.length(); i++) {
                Log.d("Array next",array.getString(i));
                JSONObject obj = array.getJSONObject(i);
                Log.d("id",obj.getString("id"));
                Log.d("name",obj.getString("name"));
                //JSONObject object = array.getJSONObject(i);
                globalitems.add(new Degree(obj.getString("name"),obj.getString("id")));
            }
            globalAreas=new ArrayList<Area>();
            JSONArray array1 = root.getJSONArray("area");
            Log.d("Array",array1.toString());
            for (int i = 0; i < array1.length(); i++) {
                Log.d("Array next",array1.getString(i));
                JSONObject obj1 = array1.getJSONObject(i);
                Log.d("id",obj1.getString("id"));
                Log.d("name",obj1.getString("name"));
                //JSONObject object = array.getJSONObject(i);
                globalAreas.add(new Area(obj1.getString("name"),obj1.getString("id")));
            }

            globalQualifications=new ArrayList<Qualification>();
            JSONArray array2 = root.getJSONArray("qualification");
            Log.d("Array",array2.toString());
            for (int i = 0; i < array2.length(); i++) {
                Log.d("Array next",array2.getString(i));
                JSONObject obj2 = array2.getJSONObject(i);
                Log.d("id",obj2.getString("id"));
                Log.d("name",obj2.getString("name"));
                //JSONObject object = array.getJSONObject(i);
                globalQualifications.add(new Qualification(obj2.getString("name"),obj2.getString("id")));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

   public void buttonClick()
    {
        Spinner spinner = (Spinner) findViewById(R.id.age_spinner);
        spinner.setBackgroundResource((R.drawable.backgroundtext));
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.age_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner

        TableLayout tl=(TableLayout)findViewById(R.id.tblEducation);
        TableRow tr1 = new TableRow(this);
        tr1.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT,TableLayout.LayoutParams.WRAP_CONTENT));
        TextView textview = new TextView(this);
        textview.setText("Degree");
        textview.setTextColor(Color.GRAY);
        // create the layout params that will be used to define how your
        // button will be displayed
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        // Create LinearLayout
            LinearLayout ll = new LinearLayout(this);
            ll.setOrientation(LinearLayout.HORIZONTAL);
            ll.addView(textview);

        Spinner spinnerDegree = new Spinner(this);

        ArrayAdapter<Degree> degreeadapter =
                new ArrayAdapter<Degree>(getApplicationContext(),  android.R.layout.simple_spinner_dropdown_item, globalitems);
        adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);

        spinnerDegree.setAdapter(degreeadapter);
        spinnerDegree.setLayoutParams(params);
        tr1.addView(spinnerDegree);
        Spinner spinnerArea = new Spinner(this);

        ArrayAdapter<Area> areaadapter =
                new ArrayAdapter<Area>(getApplicationContext(),  android.R.layout.simple_spinner_dropdown_item, globalAreas);
        adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);

        spinnerArea.setAdapter(areaadapter);
        spinnerArea.setLayoutParams(params);

        tr1.addView(spinnerArea);
        Spinner spinnerQual = new Spinner(this);

        TableRow tr2 = new TableRow(this);
        tr2.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.FILL_PARENT,TableLayout.LayoutParams.WRAP_CONTENT));

        ArrayAdapter<Qualification> qualadapter =
                new ArrayAdapter<Qualification>(getApplicationContext(),  android.R.layout.simple_spinner_dropdown_item, globalQualifications);
        adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);

        spinnerQual.setAdapter(qualadapter);
        spinnerQual.setLayoutParams(params);
        tr2.addView(spinnerQual);

        tl.addView(tr1, new TableLayout.LayoutParams(TableLayout.LayoutParams.FILL_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
        tl.addView(tr2, new TableLayout.LayoutParams(TableLayout.LayoutParams.FILL_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));

    }
}
