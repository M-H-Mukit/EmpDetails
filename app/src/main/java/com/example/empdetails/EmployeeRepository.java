package com.example.empdetails;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class EmployeeRepository {
    private EmployeeDao mEmployeeDao;
    private LiveData<List<Employee>> mAllEmployee;

    // Note that in order to unit test the WordRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples

    EmployeeRepository(Application application) {
        EmployeeRoomDatabse db= EmployeeRoomDatabse.getDatabase(application);
        mEmployeeDao=db.employeeDao();
        mAllEmployee=mEmployeeDao.getAlphabetName();

    }
    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    LiveData<List<Employee>> getmAllEmployee() {
        return mAllEmployee;
    }

    // You must call this on a non-UI thread or your app will crash.
    // Like this, Room ensures that you're not doing any long running operations on the main
    // thread, blocking the UI.
    void insert(Employee employee) {
        new insertAsyncTask(mEmployeeDao).execute(employee);
    }

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
}
