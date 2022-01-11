package com.tanvir.training.roomlibrarybatch2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.navigation.Navigator;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tanvir.training.roomlibrarybatch2.databinding.FragmentNewEmployeeBinding;
import com.tanvir.training.roomlibrarybatch2.entities.Employee;
import com.tanvir.training.roomlibrarybatch2.viewmodels.EmployeeViewModel;

public class NewEmployeeFragment extends Fragment {
    private FragmentNewEmployeeBinding binding;
    private EmployeeViewModel viewModel;
    private int id = 0;
    public NewEmployeeFragment() { }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final Employee employee = NewEmployeeFragmentArgs
                .fromBundle(getArguments()).getEmployee();
        binding = FragmentNewEmployeeBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(requireActivity())
                .get(EmployeeViewModel.class);
        if (employee != null) {
            id = employee.getId();
            binding.nameInputET.setText(employee.getEmpName());
            binding.salaryInputET.setText(String.valueOf(employee.getEmpSalary()));
            binding.saveBtn.setText("UPDATE");
        }

        binding.saveBtn.setOnClickListener(v-> {
            final String name = binding.nameInputET.getText().toString();
            final double salary = Double.parseDouble(
                    binding.salaryInputET.getText().toString());
            if (id > 0) {
                final Employee emp = new Employee(id, name, salary);
                viewModel.updateEmployee(emp);
            }else {
                final Employee emp = new Employee(name, salary);
                viewModel.addEmployee(emp);
            }

            Navigation.findNavController(v).popBackStack();
        });
        return binding.getRoot();
    }
}