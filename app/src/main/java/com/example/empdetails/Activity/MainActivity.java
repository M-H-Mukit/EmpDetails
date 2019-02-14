package com.example.empdetails.Activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.empdetails.Adapter.EmployeeListAdapter;
import com.example.empdetails.Entity.Employee;
import com.example.empdetails.Model.EmployeeViewModel;
import com.example.empdetails.R;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;

    private EmployeeViewModel mEmployeeViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        final EmployeeListAdapter adapter = new EmployeeListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mEmployeeViewModel = ViewModelProviders.of(this).get(EmployeeViewModel.class);
        // Add an observer on the LiveData returned by getAlphabetizedWords.
        // The onChanged() method fires when the observed data changes and the activity is
        // in the foreground.
        mEmployeeViewModel.getAllEmployee().observe(this, new Observer<List<Employee>>() {
            @Override
            public void onChanged(@Nullable List<Employee> employees) {
                // Update the cached copy of the words in the adapter.
                adapter.setEmployees(employees);
            }
        });


    }

    public void fabClicked(View v) {
        Intent intent = new Intent(this, AddNewEmployee.class);
        startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            String[] employee = data.getStringArrayExtra("emp");
            Employee newEmployee = new Employee(employee[0], employee[1], employee[2]
                    , employee[3], employee[4]);
            mEmployeeViewModel.insert(newEmployee);
        } else {
            Toast.makeText(
                    getApplicationContext(),
                   "Something went wrong",
                    Toast.LENGTH_LONG).show();
        }
    }

    void toastMaker(String str) {
        Toast.makeText(getApplicationContext(), str, Toast.LENGTH_LONG).show();
    }
}
