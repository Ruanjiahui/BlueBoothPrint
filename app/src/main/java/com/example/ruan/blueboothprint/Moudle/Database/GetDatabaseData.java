package com.example.ruan.blueboothprint.Moudle.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/3/17.
 */
public class GetDatabaseData extends Operation {

    /**
     * 这个是获取数据库的数据
     * <p/>
     * 返回类型是Map集合
     *
     * @param context
     * @param db
     * @param Table_Name
     * @param colums
     * @param selection
     * @param selectionArgs
     * @param groupBy
     * @param having
     * @param orderBy
     * @param limit
     * @return
     */
//    @Deprecated
    public Map<String, String> Query(Context context, String db, String Table_Name, String[] colums, String selection, String[] selectionArgs, String groupBy, String having, String orderBy, String limit) {
        Cursor cursor = query(context, db, Table_Name, colums, selection, selectionArgs, groupBy, having, orderBy, limit);

        return AnalysisCursor(cursor);
    }


    /**
     * 这个是获取多个数据的方法
     * @param context
     * @param db
     * @param Table_Name
     * @param colums
     * @param selection
     * @param selectionArgs
     * @param groupBy
     * @param having
     * @param orderBy
     * @param limit
     * @return
     */
    public ArrayList<Map<String, String>> QueryArray(Context context, String db, String Table_Name, String[] colums, String selection, String[] selectionArgs, String groupBy, String having, String orderBy, String limit) {
        Cursor cursor = query(context, db, Table_Name, colums, selection, selectionArgs, groupBy, having, orderBy, limit);

        return AnalysisCursorArray(cursor);
    }

    /**
     * 这个是更新数据库的操作
     * @param context
     * @param db
     * @param table
     * @param contentValues
     * @param whereclause
     * @param whereargs
     */
    public void Update(Context context, String db, String table, ContentValues contentValues, String whereclause, String[] whereargs){
        update(context , db , table , contentValues , whereclause , whereargs);
    }

    /**
     * 将数据插进数据库
     *
     * @param context
     * @param db
     * @param table
     * @param contentValues
     */
    public void Insert(Context context, String db, String table, ContentValues contentValues) {
        insert(context, db, table, contentValues);
    }

    /**
     * 删除表数据
     * @param context
     * @param db
     * @param table
     * @param whereclause
     * @param whereargs
     */
    public void Delete(Context context, String db, String table, String whereclause, String[] whereargs){
        delete(context, db, table, whereclause, whereargs);
    }

    /**
     * 判断数据库有没有该表
     *
     * @param context
     * @param db
     * @param Table_Name
     * @return
     */
    public boolean QueryTable(Context context, String db, String Table_Name) {
        Cursor cursor = query(context, db, Table_Name, new String[]{"count(*)"}, "", null, "", "", "", "");

        //如果拥有数据则说明有该表
        //没有数据则没有该表
        if (cursor == null)
            return false;
        return true;
    }


    private Map<String, String> AnalysisCursor(Cursor cursor) {
        Map<String, String> map = new HashMap<>();

        String[] colum = cursor.getColumnNames();

        //判断cursor是否为空

        //将cursor用游标的方式取出来
        if (cursor.moveToNext()) {
//                if (cursor.moveToFirst()) {
            //循环的方法把数据库的列名称给取出来
            for (String columName : colum) {
                map.put(columName, cursor.getString(cursor.getColumnIndex(columName)));
            }
//                }
        }
        return map;
    }


    private ArrayList<Map<String, String>> AnalysisCursorArray(Cursor cursor) {
        ArrayList<Map<String, String>> arrayList = new ArrayList<>();

        if (cursor == null)
            return null;
        String[] colum = cursor.getColumnNames();

        //判断cursor是否为空

        //将cursor用游标的方式取出来
        while (cursor.moveToNext()) {
            Map<String, String> map = new HashMap<>();
//                if (cursor.moveToFirst()) {
            //循环的方法把数据库的列名称给取出来
            for (String columName : colum) {
                map.put(columName, cursor.getString(cursor.getColumnIndex(columName)));
            }
            arrayList.add(map);
//                }
        }
        return arrayList;
    }


    /**
     * 判断数据库有没有该用户的信息
     * @param context
     * @param db
     * @param Table_Name
     * @param id
     * @return
     */
    public boolean RepeatData(Context context, String db, String Table_Name , String id){
        Map<String , String> map = Query(context , db , Table_Name , new String[]{"count(*)"} , "userid = ?" , new String[]{id} , "" , "" , "" , "");
        //数据库里面拥有给用户的数据
        if (Integer.parseInt(map.get("count(*)")) == 1){
            return true;
        }
        return false;
    }

    public boolean RepeatData(Context context, String db, String Table_Name , String id , String whereclause){
        Map<String , String> map = Query(context , db , Table_Name , new String[]{"count(*)"} , whereclause , new String[]{id} , "" , "" , "" , "");
        //数据库里面拥有给用户的数据
        if (Integer.parseInt(map.get("count(*)")) == 1){
            return true;
        }
        return false;
    }

    /**
     * 判断用户数据存在则更新，不存在插入
     * @param context
     * @param db
     * @param Table_name
     * @param id
     * @param contentValues
     * @param whereclause
     * @param whereargs
     */
    public void Insert2Update(Context context, String db, String Table_name, String id, ContentValues contentValues, String whereclause, String[] whereargs) {
        //返回false则没有改用户的数据
        //true则有该用户的数据
        if (RepeatData(context, db, Table_name, id , whereclause))
            //将用户的登录信息更新
            Update(context, db, Table_name, contentValues, whereclause, whereargs);
        else
            //将用户的登录信息写到数据库
            Insert(context, db, Table_name, contentValues);
    }
}
