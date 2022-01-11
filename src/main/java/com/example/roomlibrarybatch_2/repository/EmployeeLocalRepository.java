package com.example.roomlibrarybatch_2.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.roomlibrarybatch_2.daos.EmployeeDao;
import com.example.roomlibrarybatch_2.db.EmployeeDatabase;
import com.example.roomlibrarybatch_2.entities.Employee;

import java.util.List;

public class EmployeeLocalRepository {
    private EmployeeDao employeeDao;

    public EmployeeLocalRepository(Context context){
        // TODO: 1/2/2022 initialize employeeDao
        employeeDao = EmployeeDatabase.getInstance(context).getEmployeeDao();

    }
    public void addEmployee(Employee employee){
        employeeDao.insertEmployee(employee);
    }
    public void updateEmployee(Employee employee){
        employeeDao.updateEmployee(employee);
    }
    public void deleteEmployee(Employee employee){
        employeeDao.deleteEmployee(employee);
    }
    public LiveData<List<Employee>> getAllEmployee(){
        return employeeDao.getAllEmployees();
    }
}
