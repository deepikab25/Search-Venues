package com.foursquare.Core;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.foursquare.R;

public class DatabaseManager extends SQLiteOpenHelper {

	/*
	 * Version and name
	 */
	private static final String DATABASE_NAME = "foursquare.db";
	private static final int DATABASE_VERSION = 1;

	private static DatabaseManager mDatabaseManager = null;
	private final SQLiteDatabase mSQLdb;

	private DatabaseManager(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);

		mSQLdb = getWritableDatabase();
        mSQLdb.setForeignKeyConstraintsEnabled(true);
        mSQLdb.execSQL("PRAGMA foreign_keys=ON;");
	}

	public static synchronized DatabaseManager getInstance(Context context) {
		if (mDatabaseManager == null) {
			mDatabaseManager = new DatabaseManager(context.getApplicationContext());
		}
		return mDatabaseManager;
	}

    public static void buildSnapshot() {
        DatabaseManager mTempDatabaseManager = getInstance(BaseApplication.getBaseApplicationContext());
        String [] mSQL = BaseApplication.getBaseApplicationContext()
                .getResources().getStringArray(R.array.createsqlqueries);
		for (String query:mSQL) {
			mTempDatabaseManager.createTable(query);
		}
    }

	private void createTable(String sql) {
		synchronized (mSQLdb) {
			mSQLdb.execSQL(sql);
		}

	}

	public void insertOperation(String table_name, ContentValues cv) {
		synchronized (mSQLdb) {
			mSQLdb.insertWithOnConflict(table_name, null,
					cv, SQLiteDatabase.CONFLICT_REPLACE);
		}

	}

	public Cursor selectOperation(String sql) {
		synchronized (mSQLdb) {
			int count = 0;
			Cursor result_cursor;
			result_cursor = mSQLdb.rawQuery(sql, null);
			if(result_cursor == null) {
				while(count < 2) {
					result_cursor = mSQLdb.rawQuery(sql, null);
					if(result_cursor != null) {
						return result_cursor;
					}
					count ++;
				}
			}
			return result_cursor ;
		}
	}
	
	public void closeDB() {
		synchronized (mSQLdb) {
			mSQLdb.close();
		}
	}

	@Override
	public void onCreate(SQLiteDatabase mSQLiteDatabase) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onUpgrade(SQLiteDatabase mSQLiteDatabase, int arg1, int arg2) {
		// TODO Auto-generated method stub
	}

}
