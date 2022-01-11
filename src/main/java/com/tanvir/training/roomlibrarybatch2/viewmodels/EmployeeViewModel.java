package com.tanvir.training.roomlibrarybatch2.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.tanvir.training.roomlibrarybatch2.entities.Employee;
import com.tanvir.training.roomlibrarybatch2.repos.EmployeeLocalRepository;

import java.util.List;

public class EmployeeViewModel extends AndroidViewModel {
    private EmployeeLocalRepository repository;
    public EmployeeViewModel(@NonNull Application application) {
        super(application);
        repository = new EmployeeLocalRepository(application);
    }

    public void addEmployee(Employee employee) {
        repository.addEmployee(employee);
    }

    public void updateEmployee(Employee employee) {
        repository.updateEmployee(employee);
    }

    public void deleteEmployee(Employee employee) {
        repository.deleteEmployee(employee);
    }

    public LiveData<List<Employee>> getEmployees() {
        return repository.getEmployees();
    }
}
