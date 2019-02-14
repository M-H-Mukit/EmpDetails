package com.example.empdetails.Entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "employee_table")
public class Employee {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "first_name")
    private String firstName;

    @ColumnInfo(name = "last_name")
    private String lastName;

    public void setId(int id) {
        this.id = id;
    }

    @ColumnInfo(name = "designation")
    private String designation;


    @ColumnInfo(name = "email")
    private String email;

    @ColumnInfo(name = "phone_no")
    private String phoneNo;


    @NonNull
    public String getFirstName() {
        return firstName;
    }

    public int getId() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDesignation() {
        return designation;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public Employee(@NonNull String firstName, String lastName, String designation, String email, String phoneNo) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.designation = designation;
        this.email = email;
        this.phoneNo = phoneNo;
    }
}
