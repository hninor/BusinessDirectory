package com.example.hnino.businessdirectory;

import android.app.Application;

import com.example.hnino.businessdirectory.entities.DaoMaster;
import com.example.hnino.businessdirectory.entities.DaoSession;

import org.greenrobot.greendao.database.Database;

/**
 * Created by hnino on 13/11/2017.
 */

public class BusinessDirectoryApp extends Application{

    /** A flag to show how easily you can switch from standard SQLite to the encrypted SQLCipher. */
    public static final boolean ENCRYPTED = false;

    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, ENCRYPTED ? "notes-db-encrypted" : "notes-db");
        Database db = ENCRYPTED ? helper.getEncryptedWritableDb("super-secret") : helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}
