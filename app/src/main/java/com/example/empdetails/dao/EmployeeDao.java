package com.example.empdetails.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.empdetails.entity.Employee;

import java.util.List;

@Dao
public interface EmployeeDao {

    // LiveData is a data holder class that can be observed within a given lifecycle.
    // Always holds/caches latest version of data. Notifies its active observers when the
    // data has changed. Since we are getting all the contents of the database,
    // we are notified whenever any of the database contents have changed.
    @Query("SELECT * from employee_table ORDER BY first_name ASC")
    LiveData<List<Employee>> getAlphabetizedEmployeeName();

    @Query("SELECT * from employee_table where id=:id")
    LiveData<Employee> getEmployeeByID(int id);

    // We do not need a conflict strategy, because the word is our primary key, and you cannot
    // add two items with the same primary key to the database. If the table has more than one
    // column, you can use @Insert(onConflict = OnConflictStrategy.REPLACE) to update a row.
    @Insert
    void insert(Employee employee);

    @Query("DELETE FROM employee_table where id=:id")
    void deleteEmployeeById(int id);

    @Query("DELETE FROM employee_table")
    void deleteAll();
}
