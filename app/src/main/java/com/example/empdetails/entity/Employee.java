package com.example.empdetails.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

@Entity(tableName = "employee_table")
public class Employee implements Parcelable {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "first_name")
    private String firstName;

    @ColumnInfo(name = "last_name")
    private String lastName;

    protected Employee(Parcel in) {
        id = in.readInt();
        firstName = in.readString();
        lastName = in.readString();
        designation = in.readString();
        email = in.readString();
        phoneNo = in.readString();
    }

    public static final Creator<Employee> CREATOR = new Creator<Employee>() {
        @Override
        public Employee createFromParcel(Parcel in) {
            return new Employee(in);
        }

        @Override
        public Employee[] newArray(int size) {
            return new Employee[size];
        }
    };

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
        this.firstName = firstName;
        this.lastName = lastName;
        this.designation = designation;
        this.email = email;
        this.phoneNo = phoneNo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(firstName);
        dest.writeString(lastName);
        dest.writeString(designation);
        dest.writeString(email);
        dest.writeString(phoneNo);
    }
}
