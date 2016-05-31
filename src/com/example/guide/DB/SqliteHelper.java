package com.example.guide.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SqliteHelper extends SQLiteOpenHelper {
	public static final String LOG_TAG = "SqliteHelper";

	public static final String TB_SCENERY = "scenery";
	public static final String TB_COL_ID = "_id";
	public static final String TB_COL_NAME = "name";
	public static final String TB_COL_PROVICE = "provice";
	public static final String TB_COL_CITY = "city";
	public static final String TB_COL_LOCATION = "location";
	public static final String TB_COL_PICURL = "picUrl";

	public static final String TB_COL_INTRODUCE = "introduce";

	public SqliteHelper(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE IF NOT EXISTS " + TB_SCENERY + "(" + TB_COL_ID + " varchar primary key," + TB_COL_NAME + " varchar," + TB_COL_PROVICE + " varchar," + TB_COL_CITY + " varchar," + TB_COL_LOCATION + " varchar," + TB_COL_PICURL + " varchar"+TB_COL_INTRODUCE+" varchar" + ")");
		Log.d(LOG_TAG, "onCreate");

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TB_SCENERY);
		onCreate(db);
		Log.d(LOG_TAG, "onUpgrade");
	}

	// 更新数据库表列
	public void updateColumn(SQLiteDatabase db, String oldColumn, String newColumn, String typeColumn) {
		try {
			db.execSQL("ALTER TABLE " + TB_SCENERY + " CHANGE " + oldColumn + " " + newColumn + " " + typeColumn);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
