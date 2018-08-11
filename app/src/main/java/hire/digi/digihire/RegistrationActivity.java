package hire.digi.digihire;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;
import android.widget.TextView;

public class RegistrationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        EditText fname = (EditText)findViewById(R.id.input_fname);
        fname.setBackgroundResource(R.drawable.backgroundtext);
        EditText lname = (EditText)findViewById(R.id.input_lname);
        lname.setBackgroundResource((R.drawable.backgroundtext));
        EditText mname = (EditText)findViewById(R.id.input_mname);
        mname.setBackgroundResource((R.drawable.backgroundtext));
        EditText email = (EditText)findViewById(R.id.input_email);
        email.setBackgroundResource((R.drawable.backgroundtext));

        EditText confirmemail = (EditText)findViewById(R.id.input_confirmemail);
        confirmemail.setBackgroundResource((R.drawable.backgroundtext));

        EditText mobile = (EditText)findViewById(R.id.input_mobile);
        mobile.setBackgroundResource((R.drawable.backgroundtext));

        Button mEmailSignInButton = (Button) findViewById(R.id.btn_signup);
        mEmailSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(RegistrationActivity.this, EducationActivity.class);

                RegistrationActivity.this.startActivity(myIntent);
            }
        });
    }
}
