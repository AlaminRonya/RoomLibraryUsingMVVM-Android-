package com.example.roomlibrarybatch_2.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.roomlibrarybatch_2.entities.Employee;

import java.util.List;

@Dao
public interface EmployeeDao {
    @Insert
    void insertEmployee(Employee employee);
    @Update
    void updateEmployee(Employee employee);
    @Delete
    void deleteEmployee(Employee employee);
    @Query("SELECT * FROM tbl_employee")
    LiveData<List<Employee>> getAllEmployees();
}
