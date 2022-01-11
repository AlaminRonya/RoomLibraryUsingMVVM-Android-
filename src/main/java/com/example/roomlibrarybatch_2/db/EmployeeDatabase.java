package com.example.roomlibrarybatch_2.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.roomlibrarybatch_2.daos.EmployeeDao;
import com.example.roomlibrarybatch_2.entities.Employee;

@Database(entities = {Employee.class}, version = 1)
public abstract class EmployeeDatabase extends RoomDatabase {
    public abstract EmployeeDao getEmployeeDao();
    private static EmployeeDatabase db;
    public static EmployeeDatabase getInstance(Context context){
        if (db == null){
            db = Room.databaseBuilder(context, EmployeeDatabase.class, "emp_db").allowMainThreadQueries().build();
        }
        return db;
    }
}
