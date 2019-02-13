package com.example.empdetails;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

public class EmployeeListAdapter extends RecyclerView.Adapter<EmployeeListAdapter.EmployeeViewHolder> {

    private final LayoutInflater mInflater;
    private List<Employee> mEmployees = Collections.emptyList();

    EmployeeListAdapter(Context context) {
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
        employeeViewHolder.employeeItemView.setText(current.getEmployee());
    }

    void setWords(List<Employee> employees) {
        mEmployees = employees;
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return mEmployees.size();
    }

    class EmployeeViewHolder extends RecyclerView.ViewHolder {
        private final TextView employeeItemView;

        private EmployeeViewHolder(View itemView) {
            super(itemView);
            employeeItemView=itemView.findViewById(R.id.textView);

        }
    }


}
