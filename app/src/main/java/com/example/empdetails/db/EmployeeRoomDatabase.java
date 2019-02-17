package com.example.empdetails.db;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.example.empdetails.dao.EmployeeDao;
import com.example.empdetails.entity.Employee;

/**
 * This is the backend. The database. This used to be done by the OpenHelper.
 * The fact that this has very few comments emphasizes its coolness.
 */

@Database(entities = {Employee.class}, version = 2, exportSchema = false)
public abstract class EmployeeRoomDatabase extends RoomDatabase {

    public abstract EmployeeDao employeeDao();

    // marking the instance as volatile to ensure atomic access to the variable
    private static volatile EmployeeRoomDatabase INSTANCE;

    public static EmployeeRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (EmployeeRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            EmployeeRoomDatabase.class, "employee_database")
                            // Wipes and rebuilds instead of migrating if no Migration object.
                            // Migration is not part of this codelab.
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    /**
     * Override the onOpen method to populate the database.
     * For this sample, we clear the database every time it is created or opened.
     * <p>
     * If you want to populate the database only when the database is created for the 1st time,
     * override RoomDatabase.Callback()#onCreate
     */
    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            // If you want to keep the data through app restarts,
            // comment out the following line.
            new PopulateDbAsync(INSTANCE).execute();
        }
    };

    /**
     * Populate the database in the background.
     * If you want to start with more words, just add them.
     */
    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final EmployeeDao mDao;

        PopulateDbAsync(EmployeeRoomDatabase db) {
            mDao = db.employeeDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.
            mDao.deleteAll();

            Employee employee_name = new Employee("Fahim",
                    "Ahmed", "software dev",
                    "fahim.ahmed@gmail.com", "01684607182");
            mDao.insert(employee_name);
            employee_name = new Employee("Sabbir",
                    "Alam", "software dev",
                    "sabbir.alam@gmail.com", "01684607182");
            mDao.insert(employee_name);
            return null;
        }
    }


}
