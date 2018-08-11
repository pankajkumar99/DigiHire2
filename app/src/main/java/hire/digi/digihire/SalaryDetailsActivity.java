package hire.digi.digihire;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
import java.util.List;

public class SalaryDetailsActivity extends AppCompatActivity {
    ArrayList<City> globalcities;
    ArrayList<Industry> globalIndustries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salary_details);
        Button btn = findViewById(R.id.btnAdd);

        btn.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View view) {
                                       buttonClick();
                                   }
                               }
        );
        Button nextButton = (Button) findViewById(R.id.btn_next);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(SalaryDetailsActivity.this, MainActivity.class);

                SalaryDetailsActivity.this.startActivity(myIntent);
            }
        });
populatecombos();
sendPost();
    }
   public void populatecombos()
    {

        List experience = new ArrayList<String>();
        for (int i = 0; i <= 30; i++) {
            experience.add(Integer.toString(i));
        }
        ArrayAdapter<Integer> spinnerArrayAdapter = new ArrayAdapter<Integer>(
                this, android.R.layout.simple_spinner_item, experience);
        spinnerArrayAdapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );

        Spinner spinner = (Spinner)findViewById(R.id.experience_spinner);
        spinner.setAdapter(spinnerArrayAdapter);


        List salary = new ArrayList<String>();
        for (double i = 0; i <= 30; i+=.5) {
            salary.add(Double.toString(i));
        }
        ArrayAdapter<Integer> salaryArrayAdapter = new ArrayAdapter<Integer>(
                this, android.R.layout.simple_spinner_item, salary);
        salaryArrayAdapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );

        Spinner salaryspinner = (Spinner)findViewById(R.id.salary_spinner);
        salaryspinner.setAdapter(salaryArrayAdapter);

        List expectedSalary = new ArrayList<String>();
        for (double i = 0; i <= 30; i+=.5) {
            expectedSalary.add(Double.toString(i));
        }
        ArrayAdapter<Integer> expectedSalaryArrayAdapter = new ArrayAdapter<Integer>(
                this, android.R.layout.simple_spinner_item, expectedSalary);
        expectedSalaryArrayAdapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
        Spinner expectedsalaryspinner = (Spinner)findViewById(R.id.expectedsalary_spinner);
        expectedsalaryspinner.setAdapter(expectedSalaryArrayAdapter);

        List industry = new ArrayList<String>();
        for (double i = 0; i <= 30; i+=.5) {
            industry.add(Double.toString(i));
        }
        ArrayAdapter<Integer> industryArrayAdapter = new ArrayAdapter<Integer>(
                this, android.R.layout.simple_spinner_item, industry);
        industryArrayAdapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
        Spinner industryspinner = (Spinner)findViewById(R.id.industry_spinner);
        industryspinner.setAdapter(industryArrayAdapter);
    }
    public void sendPost() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL("https://www.digihire.in/beta2/api/candidate_salary_display");
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


            globalcities = new ArrayList<>();
            JSONObject root = new JSONObject(json_string);
            JSONArray array = root.getJSONArray("cities_list");
            Log.d("Array",array.toString());
            for (int i = 0; i < array.length(); i++) {
                Log.d("Array next",array.getString(i));
                JSONObject obj = array.getJSONObject(i);
                Log.d("id",obj.getString("city_id"));
                Log.d("name",obj.getString("city_name"));
                //JSONObject object = array.getJSONObject(i);
                globalcities.add(new City(obj.getString("city_name"),obj.getString("city_id")));
            }
            globalIndustries=new ArrayList<Industry>();
            JSONArray array1 = root.getJSONArray("industries_list");
            Log.d("Array",array1.toString());
            for (int i = 0; i < array1.length(); i++) {
                Log.d("Array next",array1.getString(i));
                JSONObject obj1 = array1.getJSONObject(i);
                Log.d("id",obj1.getString("industry_id"));
                Log.d("name",obj1.getString("industry_name"));
                //JSONObject object = array.getJSONObject(i);
                globalIndustries.add(new Industry(obj1.getString("industry_name"),obj1.getString("industry_id")));
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void buttonClick()
    {


        TableLayout tl=(TableLayout)findViewById(R.id.tbl);
        TableRow tr1 = new TableRow(this);
        tr1.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT,TableLayout.LayoutParams.WRAP_CONTENT));

        Spinner spinnerCities = new Spinner(this);

        ArrayAdapter<City> cityadapter =
                new ArrayAdapter<City>(getApplicationContext(),  android.R.layout.simple_spinner_dropdown_item, globalcities);
        cityadapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);

        spinnerCities.setAdapter(cityadapter);
        //  spinnerDegree.setLayoutParams(params);
        tr1.addView(spinnerCities);
        Spinner spinnerIndustry= new Spinner(this);

        ArrayAdapter<Industry> industryadapter =
                new ArrayAdapter<Industry>(getApplicationContext(),  android.R.layout.simple_spinner_dropdown_item, globalIndustries);
        industryadapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);

        spinnerIndustry.setAdapter(industryadapter);
        // spinnerArea.setLayoutParams(params);

        tr1.addView(spinnerIndustry);


        tl.addView(tr1, new TableLayout.LayoutParams(TableLayout.LayoutParams.FILL_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));

    }
}
