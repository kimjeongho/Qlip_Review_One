package com.example.tenqube.qlip_review_one.db.query;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.tenqube.qlip_review_one.db.DatabaseHelper;

import static com.example.tenqube.qlip_review_one.util.LogUtil.LOGI;
import static com.example.tenqube.qlip_review_one.util.LogUtil.makeLogTag;


public abstract class QueryHelper {
    public static final String TAG= makeLogTag(QueryHelper.class);
    public static final String SELECT = " SELECT ";
    public static final String DELETE = " DELETE ";
    public static final String UPDATE = " UPDATE ";
    public static final String INSERT_INTO = " INSERT INTO ";
    public static final String VALUES = " VALUES ";

    public static final String LIMIT = " LIMIT ";
    public static final String ORDER_BY = " ORDER BY ";
    public static final String FROM = " FROM ";
    public static final String WHERE = " WHERE ";
    public static final String GROUP_BY = " GROUP BY ";
    public static final String HAVING = " HAVING ";
    public static final String SET = " SET ";
    public static final String JOIN = " JOIN ";
    public static final String ON = " ON ";
    public static final String AND = " AND ";
    public static final String IN = " IN ";
    public static final String NOT_IN = " NOT IN ";
    public static final String UNION_ALL=" UNION ALL ";

    public static final String OR = " OR ";
    public static final String DESC = " DESC ";
    public static final String ASC = " ASC ";
    public static final String YYYY_MM_DD_H_M="'%Y-%m-%d %H:%M'";
    public static final String YYYY_MM_DD="'%Y-%m-%d'";
    public static final String MM_DD="'%m-%d'";
    public static final String H_M_S="'%H:%M:%S'";



    public Context mContext;
    public DatabaseHelper helper;
    public SQLiteDatabase db;
    public SQLiteDatabase wdb;

    public QueryHelper(Context context){
        this.mContext=context;
        helper=DatabaseHelper.getInstance(context);
        db = helper.getReadableDatabase();
        wdb = helper.getWritableDatabase();

    }


    /**
     * Called to insert an item.
     *
     * @param tableName
     *           삽입 되는 테이블명
     * @param values
     *            삽입 되는 값
     * @return return 삽입된 행수
     */
    public long insert(String tableName,ContentValues values) {
       try{
            return  wdb.insert(tableName, null, values);
        }catch (SQLException e){
            return  0;
        }
    }

    /**
     * Called to update an item.
     *
     * @param tableName
     *           업데이트 되는 테이블명
     * @param values
     *            업데이트 되는 값
     * @param selection
     *          where 조건 파라미터
     * @param  selectionArgs
     *          where 조건 값
     * @return return 업데이트된 행수
     */
    public long update(String tableName,ContentValues values,String selection,String[] selectionArgs) {

        try{
            return wdb.update(tableName, values, selection, selectionArgs);
        }catch (SQLException e){
            return  0;
        }
    }
//
//    /**
//     * Called to delete an item.
//     *
//     * @param tableName
//     *           삭제 되는 테이블명
//
//     * @param selection
//     *          where 조건 파라미터
//     * @param  selectionArgs
//     *          where 조건 값
//     * @return return 삭제된 행수
//     */
    public long delete(String tableName,String selection,String[] selectionArgs) {
        try {
            return wdb.delete(tableName, selection, selectionArgs);
        }catch (SQLException e){
            return  0;
        }
    }
/*
    public void delete(String tableName, String selection, String ids){
        db.execSQL("DELETE FROM " + table + " WHERE " + id + " IN " + "("+ids+")");

}*/


    public Cursor runQuery(final String query) {
        Cursor rows;
        rows = db.rawQuery(query, null);


        if (rows == null) {
            return null;
        }

        try {
            final int rowCount = rows.getCount();
            LOGI(TAG, "Query : " + query + " rowCount: "+rowCount );

            if (rowCount == 0 || !rows.moveToLast()) {
                rows.close();
                return null;
            }
        } catch (RuntimeException ex) {
            rows.close();
            return null;
        }


        return  rows;
    }




}
