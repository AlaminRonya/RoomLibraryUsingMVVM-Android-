package com.tanvir.training.roomlibrarybatch2.repos;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.tanvir.training.roomlibrarybatch2.daos.EmployeeDao;
import com.tanvir.training.roomlibrarybatch2.db.EmployeeDatabase;
import com.tanvir.training.roomlibrarybatch2.entities.Employee;

import java.util.List;

public class EmployeeLocalRepository {
    private EmployeeDao employeeDao;

    public EmployeeLocalRepository(Context context) {
        employeeDao = EmployeeDatabase.getDb(context).getEmployeeDao();
    }

    public void addEmployee(Employee employee) {
        employeeDao.insertEmployee(employee);
    }

    public void updateEmployee(Employee employee) {
        employeeDao.updateEmployee(employee);
    }

    public void deleteEmployee(Employee employee) {
        employeeDao.deleteEmployee(employee);
    }

    public LiveData<List<Employee>> getEmployees() {
        return employeeDao.getAllEmployees();
    }
}
