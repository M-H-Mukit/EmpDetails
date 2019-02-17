package com.example.empdetails.model;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.empdetails.repository.EmployeeRepository;
import com.example.empdetails.entity.Employee;

import java.util.List;

public class EmployeeViewModel extends AndroidViewModel {

    private EmployeeRepository mRepository;
    // Using LiveData and caching what getAlphabetizedWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    private LiveData<List<Employee>> mAllEmployee;

    public EmployeeViewModel(@NonNull Application application) {
        super(application);
        mRepository = new EmployeeRepository(application);
        mAllEmployee = mRepository.getAllEmployee();
    }

    public LiveData<List<Employee>> getAllEmployee() {
        return mAllEmployee;
    }

    public void insert(Employee employee){
        mRepository.insert(employee);
    }

    public void deleteEmployee(Employee employee)
    {
        mRepository.delete(employee);
    }

}
