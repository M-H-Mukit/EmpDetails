package com.example.empdetails.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.empdetails.R;
import com.example.empdetails.entity.Employee;

public class EmployeeDetailsActivity extends AppCompatActivity {

    TextView nameTextView;
    TextView designationView;
    TextView emailView;
    TextView phoneView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_details_view);

        nameTextView=findViewById(R.id.employee_name);
        designationView=findViewById(R.id.employee_designation);
        emailView=findViewById(R.id.employee_email);
        phoneView=findViewById(R.id.employee_phone_no);

        if(getIntent().hasExtra("selected_emp")){
            Employee employee=getIntent().getParcelableExtra("selected_emp");
            nameTextView.setText(employee.getFirstName()+' '+employee.getLastName());
            designationView.setText(employee.getDesignation());
            emailView.setText(employee.getEmail());
            phoneView.setText(employee.getPhoneNo());
        }
        else{
            toastMaker("No data found");
        }


    }


    void toastMaker(String str) {
        Toast.makeText(getApplicationContext(), str, Toast.LENGTH_LONG).show();
    }
}
