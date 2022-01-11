package com.example.roomlibrarybatch_2.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.roomlibrarybatch_2.entities.Employee;
import com.example.roomlibrarybatch_2.repository.EmployeeLocalRepository;

import java.util.List;

public class EmployeeViewModel extends AndroidViewModel {
    private EmployeeLocalRepository repository;

    public EmployeeViewModel(@NonNull Application application) {
        super(application);
        repository = new EmployeeLocalRepository(application);
    }

    public void addEmployee(Employee employee){
        repository.addEmployee(employee);
    }
    public void updateEmployee(Employee employee){
        repository.updateEmployee(employee);
    }
    public void deleteEmployee(Employee employee){
        repository.deleteEmployee(employee);
    }
    public LiveData<List<Employee>> getAllEmployee(){
        return repository.getAllEmployee();
    }

}
