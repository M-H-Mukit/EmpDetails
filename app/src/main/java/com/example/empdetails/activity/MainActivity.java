package com.example.empdetails.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


import com.example.empdetails.R;
import com.example.empdetails.adapter.EmployeeListAdapter;
import com.example.empdetails.entity.Employee;
import com.example.empdetails.model.EmployeeViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;

    private EmployeeViewModel mEmployeeViewModel;

    String TAG = "MainActivity";
    List<Employee> employeeList;
    EmployeeListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        adapter = new EmployeeListAdapter(this);
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
                employeeList=employees;
                adapter.setEmployees(employees);
            }
        });

        adapter.setOnItemClickListener(new EmployeeListAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Employee selectedEmployee = adapter.getEmployee(position);
                Log.d(TAG, "onItemClick position: " + selectedEmployee.getId());

                Intent intent = new Intent(v.getContext(), EmployeeDetailsActivity.class);
                intent.putExtra("selected_emp", selectedEmployee);
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(int position, View v) {
                Log.d(TAG, "onItemLongClick pos = " + position);
                showAlert(v.getContext(),adapter,position);
            }
        });


    }


    public void onFabClicked(View v) {
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
        }
    }

    public void showAlert(Context context, final EmployeeListAdapter adapter, final int posiiton) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(R.string.app_name);
        builder.setMessage("Do you want to delete Employee ?");
        builder.setIcon(R.drawable.ic_launcher_background);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
                Employee employee = adapter.getEmployee(posiiton);
                mEmployeeViewModel.deleteEmployee(employee);

            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();

    }

    void toastMaker(String str) {
        Toast.makeText(getApplicationContext(), str, Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu,menu);
        MenuItem menuItem =menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        String userInput = newText.toLowerCase();
        List<Employee> newList = new ArrayList<>();

        for(Employee name : employeeList){
            if(name.getFirstName().toLowerCase().contains(userInput)){
                newList.add(name);
            }
        }
        adapter.updateList(newList);

        return true;
    }
}
