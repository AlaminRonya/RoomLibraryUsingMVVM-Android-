package com.example.roomlibrarybatch_2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.roomlibrarybatch_2.databinding.FragmentNewEmployeeBinding;
import com.example.roomlibrarybatch_2.entities.Employee;
import com.example.roomlibrarybatch_2.viewmodel.EmployeeViewModel;

public class NewEmployeeFragment extends Fragment {
    private FragmentNewEmployeeBinding binding;
    private EmployeeViewModel employeeViewModel;
    private int id = 0;
    public NewEmployeeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentNewEmployeeBinding.inflate(inflater, container, false);
        employeeViewModel = new ViewModelProvider(requireActivity()).get(EmployeeViewModel.class);



        binding.saveBtn.setOnClickListener(view -> {
            final String name = binding.nameInputET.getText().toString();
            final double salary = Double.parseDouble(binding.salaryInputET.getText().toString());
            final Employee employee = new Employee(name, salary);
            employeeViewModel.addEmployee(employee);
            Navigation.findNavController(view).popBackStack();
        });








        return binding.getRoot();
    }
}