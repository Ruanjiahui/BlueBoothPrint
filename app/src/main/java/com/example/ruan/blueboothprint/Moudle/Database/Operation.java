package com.example.ruan.blueboothprint.Moudle.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Administrator on 2016/2/29.
 * <p/>
 * 该表用于数据库操作的类
 */
public class Operation {

    private static SQLiteDatabase sqLiteDatabase = null;
    private static DatabaseHelper databaseHelper = null;

    /**
     * 添加数据库数据
     *
     * @param context
     * @param db
     * @param table
     * @param contentValues
     */
    protected void insert(Context context, String db, String table, ContentValues contentValues) {
        databaseHelper = CreateTable.getInstance(context, db);
        sqLiteDatabase = databaseHelper.getWritableDatabase();

        sqLiteDatabase.insert(table, null, contentValues);
    }

    /**
     * 更新数据库数据
     *
     * @param context
     * @param db
     * @param table
     * @param contentValues
     * @param whereclause
     * @param whereargs
     */
    protected void update(Context context, String db, String table, ContentValues contentValues, String whereclause, String[] whereargs) {
        databaseHelper = CreateTable.getInstance(context, db);
        sqLiteDatabase = databaseHelper.getWritableDatabase();

        sqLiteDatabase.update(table, contentValues, whereclause, whereargs);
    }

    /**
     * 查询数据库
     *
     * @param context
     * @param db
     * @param Table_Name
     * @param colums        获取数据的列名称
     * @param selection     where后面的内容
     * @param selectionArgs where后面内容的参数
     * @param groupBy       GROUP BY后面的字符串
     * @param having        HAVING后面的字符串
     * @param orderBy       ORDER BY后面的字符串
     * @param limit         返回的数量
     * @return
     */
    protected Cursor query(Context context, String db, String Table_Name, String[] colums, String selection, String[] selectionArgs, String groupBy, String having, String orderBy, String limit) {
        databaseHelper = CreateTable.getInstance(context, db);
        sqLiteDatabase = databaseHelper.getWritableDatabase();

        try {
            return sqLiteDatabase.query(Table_Name, colums, selection, selectionArgs, groupBy, having, orderBy, limit);
        } catch (Exception e) {
            return null;
        }

    }

    /**
     * 删除表数据和删除但条数据
     * @param context
     * @param db
     * @param Table_Name
     * @param whereclause
     * @param whereargs
     */
    protected void delete(Context context, String db, String Table_Name, String whereclause, String[] whereargs){
        databaseHelper = CreateTable.getInstance(context, db);
        sqLiteDatabase = databaseHelper.getWritableDatabase();

        sqLiteDatabase.delete(Table_Name , whereclause , whereargs);
    }

    /**
     * 判断有没有这个表
     *
     * @param context
     * @param db
     * @param table
     * @return
     */
    protected boolean TableVisiable(Context context, String db, String table) {
        //这句的意思就是"select count(*) from table"
        Cursor cursor = query(context, db, table, new String[]{"count(*)"}, null, null, null, null, null, null);
        //循环读取cursor的数据
        if (cursor.moveToNext()) {
            //如果数据为0则视为没有该表
            if (cursor.getInt(0) == 0) {
                return false;
            }
            //否则则有该表
            return true;
        }
        return false;
    }

    /**
     * 判断表有没有这条数据
     *
     * @param context
     * @param db
     * @param table
     * @param key
     * @param value
     * @return
     */
    protected boolean DataVisiable(Context context, String db, String table, String key, String[] value) {
        //这句的意思就是"select count(*) from table"
        Cursor cursor = query(context, db, table, new String[]{"count(*)"}, key + "=?", value, null, null, null, null);
        //循环读取cursor的数据
        if (cursor.moveToNext()) {
            //如果数据为0则视为没有该表
            if (cursor.getInt(0) == 0) {
                return false;
            }
            //否则则有该表
            return true;
        }
        return false;
    }
}
