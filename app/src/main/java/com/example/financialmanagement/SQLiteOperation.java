package com.example.financialmanagement;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class SQLiteOperation {

    public long add(Context context, String category, String amount, String date, String remark){
        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        MySQLiteOpenHelper helper = new MySQLiteOpenHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues v = new ContentValues();
        v.put("UUID", uuid);
        v.put("category", category);
        v.put("amount", amount);
        v.put("date", date);
        v.put("remark", remark);
        long res = db.insert("Info", null, v);
        db.close();
        return res;
    }

    public void update(Context context, String uuid, String name, String value){
        MySQLiteOpenHelper helper = new MySQLiteOpenHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues v = new ContentValues();
        v.put(name, value);
        String[] whereArgs= {uuid}; //查询条件的参数值
        db.update("Info", v, "UUID" , whereArgs);
    }

    public List<Map<String, Object>> query(Context context, String tablename) {
        List<Map<String, Object>> datalist = new ArrayList<Map<String, Object>>();
        MySQLiteOpenHelper helper = new MySQLiteOpenHelper(context);
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.query("Info", new String[]{"*"},null, null, null, null, null);
        while (cursor.moveToNext()) {
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("UUID", cursor.getString(cursor.getColumnIndex("UUID")));
            data.put("category", cursor.getString(cursor.getColumnIndex("category")));
            data.put("amount", cursor.getString(cursor.getColumnIndex("amount")));
            data.put("date", cursor.getString(cursor.getColumnIndex("date")));
            data.put("remark", cursor.getString(cursor.getColumnIndex("remark")));
            datalist.add(data);
        }
        return datalist;
    }

    public List<Map<String, Object>> query(Context context, String tablename, String Date) {
        List<Map<String, Object>> datalist = new ArrayList<Map<String, Object>>();
        MySQLiteOpenHelper helper = new MySQLiteOpenHelper(context);
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.query("Info", new String[]{"*"},"date like ?", new String[]{"%"+Date+"%"}, null, null, null);
        while (cursor.moveToNext()) {
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("UUID", cursor.getString(cursor.getColumnIndex("UUID")));
            data.put("category", cursor.getString(cursor.getColumnIndex("category")));
            data.put("amount", cursor.getString(cursor.getColumnIndex("amount")));
            data.put("date", cursor.getString(cursor.getColumnIndex("date")));
            data.put("remark", cursor.getString(cursor.getColumnIndex("remark")));
            datalist.add(data);
        }
        return datalist;
    }

    public void delete(Context context, String tablename, String UUID){
        MySQLiteOpenHelper helper = new MySQLiteOpenHelper(context);
        SQLiteDatabase db = helper.getReadableDatabase();
        //db.delete(tablename, "UUID > ?", new String[] {UUID});
        String sql = "DELETE FROM " + tablename + " WHERE UUID = 'UUID';";
        db.execSQL(sql);
        db.close();
        System.out.println(UUID);
    }
}
