package com.example.empdetails;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "employee_table")
public class Employee {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "employee_fName")
    private String mEmployee;

    public Employee(String name) {this.mEmployee = name;}

    public String getEmployee(){return this.mEmployee;}



}
