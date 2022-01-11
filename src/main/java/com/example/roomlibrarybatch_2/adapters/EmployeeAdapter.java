package com.example.roomlibrarybatch_2.adapters;

import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roomlibrarybatch_2.R;
import com.example.roomlibrarybatch_2.callbacks.EmployeeActionListener;
import com.example.roomlibrarybatch_2.databinding.EmployeeRowBinding;
import com.example.roomlibrarybatch_2.entities.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder>{
//    List<Employee> employeeList;
    private EmployeeActionListener listener;
    List<Employee> employeeList = new ArrayList<>();
    public void submittedNewList(List<Employee> employeeList){
        this.employeeList = employeeList;
        notifyDataSetChanged();
    }

//    public EmployeeAdapter(List<Employee> employeeList) {
//        this.employeeList = employeeList;
//    }
    public EmployeeAdapter(Fragment fragment){
        listener = (EmployeeActionListener) fragment;
    }

    @NonNull
    @Override
    public EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final EmployeeRowBinding binding = EmployeeRowBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);

        return new EmployeeViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeViewHolder holder, int position) {
        final Employee employee = employeeList.get(position);
        holder.bind(employee);

    }

    @Override
    public int getItemCount() {
        if (employeeList != null){
            return employeeList.size();
        }
        return 0;
    }

    class EmployeeViewHolder extends RecyclerView.ViewHolder{
        private EmployeeRowBinding binding;

        public EmployeeViewHolder(EmployeeRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            this.binding.rowMenuBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final Employee employee = employeeList.get(getAdapterPosition());
                    final PopupMenu popupMenu = new PopupMenu(view.getContext(), view);
                    final MenuInflater inflater = popupMenu.getMenuInflater();
                    inflater.inflate(R.menu.employee_row_menu, popupMenu.getMenu());
                    popupMenu.show();
                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem menuItem) {
                            switch (itemView.getId()){
                                case R.id.item_edit:
                                    listener.onEditEmployee(employee);
                                    return true;
                                case R.id.item_delete:
                                    listener.onDeleteEmployee(employee);
                                    return true;

                            }
                            return false;
                        }
                    });
                }
            });
        }

        public void bind(Employee employee) {
//            binding.nameTV.setText(employee.getName());
//            binding.salaryTV.setText(""+employee.getSalary());
            binding.setEmp(employee);
        }
    }
}
