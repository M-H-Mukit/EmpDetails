package com.example.empdetails.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.empdetails.entity.Employee;
import com.example.empdetails.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EmployeeListAdapter extends RecyclerView.Adapter<EmployeeListAdapter.EmployeeViewHolder> {

    private final LayoutInflater mInflater;
    private List<Employee> mEmployees = Collections.emptyList();

    private static ClickListener clickListener;

    public EmployeeListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public EmployeeViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, viewGroup, false);

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

    public Employee getEmployee(int position) {
        return mEmployees.get(position);
    }

    @Override
    public int getItemCount() {
        return mEmployees.size();
    }

    public void updateList(List<Employee> newList){
        mEmployees = new ArrayList<>();
        mEmployees.addAll(newList);
        notifyDataSetChanged();

    }

    class EmployeeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        private final TextView firstName;
        private final TextView lastName;
        private final ImageView person_image;


        private EmployeeViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);

            firstName = itemView.findViewById(R.id.first_name_view);
            lastName = itemView.findViewById(R.id.second_name_view);
            person_image = itemView.findViewById(R.id.person_view);

        }

        @Override
        public void onClick(View v) {
            clickListener.onItemClick(getAdapterPosition(), v);
        }

        @Override
        public boolean onLongClick(View v) {
            clickListener.onItemLongClick(getAdapterPosition(), v);
            return false;
        }
    }

    public void setOnItemClickListener(ClickListener clickListener) {
        EmployeeListAdapter.clickListener = clickListener;
    }

    public interface ClickListener {
        void onItemClick(int position, View v);

        void onItemLongClick(int position, View v);
    }


}
