package com.example.roomlibrarybatch_2;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.roomlibrarybatch_2.adapters.EmployeeAdapter;
import com.example.roomlibrarybatch_2.callbacks.EmployeeActionListener;
import com.example.roomlibrarybatch_2.databinding.FragmentHomeBinding;
import com.example.roomlibrarybatch_2.entities.Employee;
import com.example.roomlibrarybatch_2.viewmodel.EmployeeViewModel;

import java.util.List;

public class HomeFragment extends Fragment implements EmployeeActionListener {
    private FragmentHomeBinding binding;
    private EmployeeViewModel employeeViewModel;
    public HomeFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        employeeViewModel = new ViewModelProvider(requireActivity()).get(EmployeeViewModel.class);
        final EmployeeAdapter adapter = new EmployeeAdapter(this);

        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        binding.recyclerViewID.setLayoutManager(linearLayoutManager);


        employeeViewModel.getAllEmployee().observe(getViewLifecycleOwner(), employees ->{
                    Toast.makeText(getActivity(), "Size : "+employees.size(), Toast.LENGTH_SHORT).show();
//            final EmployeeAdapter employeeAdapter = new EmployeeAdapter(employees);

            adapter.submittedNewList(employees);
            binding.recyclerViewID.setAdapter(adapter);


        });


        binding.floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_newEmployeeFragment);
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onEditEmployee(Employee employee) {
        final HomeFragmentDirections.ActionHomeFragmentToNewEmployeeFragment homeFragmentDirections = HomeFragmentDirections.actionHomeFragmentToNewEmployeeFragment();

        Navigation.findNavController(getActivity(), R.id.rowMenuBtn);
    }

    @Override
    public void onDeleteEmployee(Employee employee) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setIcon(R.drawable.ic_baseline_delete_forever_24);
        builder.setTitle("Delete"+employee.getName()+" ?");
        builder.setMessage("hiuhuihii");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                employeeViewModel.deleteEmployee(employee);
            }
        });

    }
}