package com.example.guide.DB;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.guide.bean.Scenery;

public class SceneryDB {

	private static final String LOG_TAG = "SceneryDB";

	private static final String DB_NAME = "scenery.db";
	private static int DB_VERSION = 2;
	private SQLiteDatabase db;
	public static SqliteHelper dbHelper = null;

	public SceneryDB(Context context) {
		if (dbHelper == null) {
			dbHelper = new SqliteHelper(context, DB_NAME, null, DB_VERSION);
		}
		db = dbHelper.getWritableDatabase();
	}

	/**
	 * 关闭数据库
	 */
	public void Close() {
		db.close();
		dbHelper.close();
	}

	/**
	 * 获取所有Scenery列表
	 * 
	 * @return
	 */
	public List<Scenery> getAllSceneryList() {
		List<Scenery> userList = new ArrayList<Scenery>();
		Cursor cursor = db.query(SqliteHelper.TB_SCENERY, null, null, null, null, null, null);
		cursor.moveToFirst();
		while (!cursor.isAfterLast() && (cursor.getString(1) != null)) {
			Scenery scenery = new Scenery();
			scenery.setId(cursor.getString(0));
			scenery.setName(cursor.getString(1));
			scenery.setProvince(cursor.getString(2));
			scenery.setCity(cursor.getString(3));
			scenery.setLocation(cursor.getString(4));
			scenery.setPicUrl(cursor.getString(5));
			scenery.setIntroduce(cursor.getString(6));
			userList.add(scenery);
			cursor.moveToNext();
		}
		cursor.close();
		return userList;
	}

	/**
	 * 插入一条Scenery
	 * 
	 * @param scenery
	 * @return
	 */
	public Long insertOneScenery(Scenery scenery) {
		ContentValues values = new ContentValues();
		values.put(SqliteHelper.TB_COL_ID, UUID.randomUUID().toString());
		values.put(SqliteHelper.TB_COL_NAME, scenery.getName());
		values.put(SqliteHelper.TB_COL_PROVICE, scenery.getProvince());
		values.put(SqliteHelper.TB_COL_CITY, scenery.getCity());
		values.put(SqliteHelper.TB_COL_LOCATION, scenery.getLocation());
		values.put(SqliteHelper.TB_COL_PICURL, scenery.getPicUrl());
		values.put(SqliteHelper.TB_COL_INTRODUCE, scenery.getIntroduce());

		Long uid = db.insert(SqliteHelper.TB_SCENERY, SqliteHelper.TB_COL_ID, values);
		Log.e(LOG_TAG, "SaveUserInfo " + uid);

		return uid;
	}

	/**
	 * 删除一条Scenery
	 * 
	 * @param SceneryId
	 * @return
	 */
	public int deleteOneSenery(String SceneryId) {
		int id = db.delete(SqliteHelper.TB_SCENERY, SqliteHelper.TB_COL_ID + "=?", new String[] { SceneryId });
		Log.e(LOG_TAG, "DelSeneryInfo " + id);
		return id;
	}

	/**
	 * 获取一条Scenery,通过数据库中查询
	 * 
	 * @param provice
	 * @param city
	 * @param name
	 * @param sceneryList
	 * @return
	 */
	public Scenery getOneSceneryInDB(String provice, String city, String name) {
		Scenery scenery = new Scenery();
		String[] columns = null;
		String selection = "provice=? and city=? and name=?";
		String[] selectionArgs = { provice, city, name };
		String groupBy = null;
		String having = null;
		String orderBy = null;
		Cursor cursor = db.query(SqliteHelper.TB_SCENERY, columns, selection, selectionArgs, groupBy, having, orderBy);
		cursor.moveToFirst();
		while (!cursor.isAfterLast() && (cursor.getString(1) != null)) {
			scenery.setId(cursor.getString(0));
			scenery.setName(cursor.getString(1));
			scenery.setProvince(cursor.getString(2));
			scenery.setCity(cursor.getString(3));
			scenery.setLocation(cursor.getString(4));
			scenery.setPicUrl(cursor.getString(5));
			scenery.setIntroduce(cursor.getString(6));
			cursor.moveToNext();
		}
		cursor.close();

		return scenery;
	}

	/**
	 * 获取同市区Scenery,通过数据库中查询
	 * 
	 * @param provice
	 * @param city
	 * @param name
	 * @param sceneryList
	 * @return
	 */
	public List<Scenery> getCitySceneryInDB(String provice, String city) {
		ArrayList<Scenery> arrayList = new ArrayList<Scenery>();
		Scenery scenery;
		String[] columns = null;
		String selection = "provice=? and city=? ";
		String[] selectionArgs = { provice, city };
		String groupBy = null;
		String having = null;
		String orderBy = null;
		Cursor cursor = db.query(SqliteHelper.TB_SCENERY, columns, selection, selectionArgs, groupBy, having, orderBy);
		cursor.moveToFirst();
		while (!cursor.isAfterLast() && (cursor.getString(1) != null)) {
			scenery = new Scenery();
			scenery.setId(cursor.getString(0));
			scenery.setName(cursor.getString(1));
			scenery.setProvince(cursor.getString(2));
			scenery.setCity(cursor.getString(3));
			scenery.setLocation(cursor.getString(4));
			scenery.setPicUrl(cursor.getString(5));
			scenery.setIntroduce(cursor.getString(6));
			arrayList.add(scenery);
			cursor.moveToNext();
		}
		cursor.close();
		return arrayList;
	}

}
