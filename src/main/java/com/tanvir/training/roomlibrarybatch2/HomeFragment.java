package com.tanvir.training.roomlibrarybatch2;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tanvir.training.roomlibrarybatch2.adapters.EmployeeAdapter;
import com.tanvir.training.roomlibrarybatch2.callback.EmployeeActionListener;
import com.tanvir.training.roomlibrarybatch2.databinding.FragmentHomeBinding;
import com.tanvir.training.roomlibrarybatch2.entities.Employee;
import com.tanvir.training.roomlibrarybatch2.viewmodels.EmployeeViewModel;

public class HomeFragment extends Fragment implements EmployeeActionListener {
    private FragmentHomeBinding binding;
    private EmployeeViewModel viewModel;
    public HomeFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(requireActivity())
                .get(EmployeeViewModel.class);
        final EmployeeAdapter adapter = new EmployeeAdapter(this);
        binding.employeeRV.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.employeeRV.setAdapter(adapter);
        viewModel.getEmployees().observe(getViewLifecycleOwner(),
                employees -> {
                    adapter.submitNewList(employees);
                });

        binding.newEmpFab.setOnClickListener(v-> {
            Navigation.findNavController(v)
                    .navigate(R.id.new_emp_fragment_action);
        });
        return binding.getRoot();
    }

    @Override
    public void onEditEmployee(Employee employee) {
        final HomeFragmentDirections.NewEmpFragmentAction action =
                HomeFragmentDirections.newEmpFragmentAction();
        action.setEmployee(employee);
        Navigation.findNavController(getActivity(),
                R.id.fragmentContainerView)
                .navigate(action);
    }

    @Override
    public void onDeleteEmployee(Employee employee) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setIcon(R.drawable.ic_baseline_delete_forever_24);
        builder.setTitle("Delete "+employee.getEmpName()+"?");
        builder.setMessage("You are about to delete this employee. Press Yes to confirm action");
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                viewModel.deleteEmployee(employee);
            }
        });
        builder.setNegativeButton("NO", null);
        final AlertDialog dialog = builder.create();
        dialog.show();
    }
}