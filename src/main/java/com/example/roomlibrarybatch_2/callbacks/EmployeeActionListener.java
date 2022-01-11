package com.example.roomlibrarybatch_2.callbacks;

import com.example.roomlibrarybatch_2.entities.Employee;

public interface EmployeeActionListener {
    void onEditEmployee(Employee employee);
    void onDeleteEmployee(Employee employee);

}
