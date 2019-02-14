package com.example.empdetails.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.empdetails.Entity.Employee;
import com.example.empdetails.R;

import java.util.Collections;
import java.util.List;

public class EmployeeListAdapter extends RecyclerView.Adapter<EmployeeListAdapter.EmployeeViewHolder> {

    private final LayoutInflater mInflater;
    private List<Employee> mEmployees = Collections.emptyList();

    public EmployeeListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public EmployeeViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item,viewGroup,false);

        return new EmployeeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeViewHolder employeeViewHolder, int position) {
        Employee current = mEmployees.get(position);
        employeeViewHolder.firstName.setText(current.getFirstName());
        employeeViewHolder.lastName.setText(current.getLastName());
        employeeViewHolder.person_image.setImageResource(R.drawable.person);
    }

    public void setEmployees(List<Employee> employees) {
        mEmployees = employees;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mEmployees.size();
    }

    class EmployeeViewHolder extends RecyclerView.ViewHolder {
        private final TextView firstName;
        private final TextView lastName;
        private final ImageView person_image;


        private EmployeeViewHolder(View itemView) {
            super(itemView);
            firstName =itemView.findViewById(R.id.first_name_view);
            lastName=itemView.findViewById(R.id.second_name_view);
            person_image=itemView.findViewById(R.id.person_view);

        }
    }


}
