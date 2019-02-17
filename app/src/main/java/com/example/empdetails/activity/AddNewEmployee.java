package com.example.empdetails.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.empdetails.model.EmployeeViewModel;
import com.example.empdetails.R;

public class AddNewEmployee extends AppCompatActivity {

    private EditText editFirstNameView;
    private EditText editLastNameView;
    private EditText editEmailView;
    private EditText editPhoneNumberView;
    private Spinner spinner;

    private EmployeeViewModel mEmployee;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_employee);

        editFirstNameView = findViewById(R.id.first_name_input);
        editLastNameView = findViewById(R.id.last_name_input);
        editEmailView = findViewById(R.id.email_input);
        editPhoneNumberView = findViewById(R.id.phone_no_input);

        spinner = findViewById(R.id.designation_spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.employee_designation, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);

    }


    public void onClickEmployeeButton(View view) {
        Intent replyIntent = new Intent(this,MainActivity.class);
        if (TextUtils.isEmpty(editFirstNameView.getText())) {
            toastMaker("First name field is empty");
        } else if (TextUtils.isEmpty(editLastNameView.getText())) {
            toastMaker("Last name field is empty");
        } else if (TextUtils.isEmpty(editEmailView.getText())) {
            toastMaker("Email field is empty");
        } else if (TextUtils.isEmpty(editPhoneNumberView.getText())) {
            toastMaker("Phone number field is empty");
        } else {
            String designation = spinner.getSelectedItem().toString();
            String firstName = editFirstNameView.getText().toString();
            String lastName = editLastNameView.getText().toString();
            String email = editEmailView.getText().toString();
            String phoneNo = editPhoneNumberView.getText().toString();

            String[] employee= new String[] {firstName, lastName, designation, email, phoneNo};

            replyIntent.putExtra("emp", employee);
            setResult(RESULT_OK, replyIntent);
            finish();
        }


    }

    void toastMaker(String str) {
        Toast.makeText(getApplicationContext(), str, Toast.LENGTH_LONG).show();
    }
}
