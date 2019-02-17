package com.example.empdetails.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.empdetails.dao.EmployeeDao;
import com.example.empdetails.db.EmployeeRoomDatabase;
import com.example.empdetails.entity.Employee;

import java.util.List;

public class EmployeeRepository {
    private EmployeeDao mEmployeeDao;
    private LiveData<List<Employee>> mAllEmployee;

    // Note that in order to unit test the WordRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples

    public EmployeeRepository(Application application) {
        EmployeeRoomDatabase db= EmployeeRoomDatabase.getDatabase(application);
        mEmployeeDao=db.employeeDao();
        mAllEmployee=mEmployeeDao.getAlphabetizedEmployeeName();

    }
    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    public LiveData<List<Employee>> getAllEmployee() {
        return mAllEmployee;
    }

    // You must call this on a non-UI thread or your app will crash.
    // Like this, Room ensures that you're not doing any long running operations on the main
    // thread, blocking the UI.
    public void insert(Employee employee) {
        new insertAsyncTask(mEmployeeDao).execute(employee);
    }
    public void delete(Employee employee){new deleteAsyncTask(mEmployeeDao).execute(employee);}

    //asyncTask
    private static class insertAsyncTask extends AsyncTask<Employee, Void, Void> {

        private EmployeeDao mAsyncTaskDao;

        insertAsyncTask(EmployeeDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Employee... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class deleteAsyncTask extends AsyncTask<Employee, Void, Void> {

        private EmployeeDao mAsyncTaskDao;

        deleteAsyncTask(EmployeeDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Employee... params) {
            mAsyncTaskDao.deleteEmployeeById(params[0].getId());
            return null;
        }
    }
}
