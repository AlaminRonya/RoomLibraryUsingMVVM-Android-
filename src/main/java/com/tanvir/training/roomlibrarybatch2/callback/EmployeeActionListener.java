package com.tanvir.training.roomlibrarybatch2.callback;

import com.tanvir.training.roomlibrarybatch2.entities.Employee;

public interface EmployeeActionListener {
    void onEditEmployee(Employee employee);
    void onDeleteEmployee(Employee employee);
}
