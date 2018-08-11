package hire.digi.digihire;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class SalaryDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salary_details);

populatecombos();

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
    }
}
