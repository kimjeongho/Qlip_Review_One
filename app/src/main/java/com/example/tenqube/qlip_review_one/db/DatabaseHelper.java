package com.example.tenqube.qlip_review_one.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.tenqube.qlip_review_one.db.table.CardTable;

import static com.example.tenqube.qlip_review_one.util.LogUtil.LOGE;
import static com.example.tenqube.qlip_review_one.util.LogUtil.makeLogTag;


public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = ".test.db";
    private static final int DATABASE_VERSION = 1;
    protected static final String TAG=makeLogTag(DatabaseHelper.class);

    private static DatabaseHelper mInstance = null;

    public static DatabaseHelper getInstance(Context ctx) {
        if (mInstance == null) {
            mInstance = new DatabaseHelper(ctx.getApplicationContext());
        }
        return mInstance;
    }


    private DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
//            db.execSQL(OilTable.SQL_CREATE_ENTRIES);
//            db.execSQL(BudgetTable.SQL_CREATE_BUDGET_TABLE);
            db.execSQL(CardTable.SQL_CREATE_CARD_TABLE);
//            db.execSQL(CategoryCodeTable.SQL_CREATE_CATEGORY_ENTRIES);
//            db.execSQL(InstallmentTable.SQL_CREATE_ENTRIES);
//            db.execSQL(OilNearByCompanyTable.SQL_CREATE_ENTRIES);
//            db.execSQL(SMSTable.SQL_CREATE_SMS_TABLE);
//            db.execSQL(TransactionsTable.SQL_CREATE_ENTRIES);
//            db.execSQL(FullSmsTable.SQL_CREATE_TABLE);
//            db.execSQL(UserTable.SQL_CREATE_USER_TABLE);
//            db.execSQL(TestTable.SQL_CREATE_SMS_TABLE);
//            db.execSQL(HashTagTable.SQL_CREATE_LABEL_TABLE);
//            db.execSQL(GoogleCalendarTable.SQL_CREATE_TABLE);
//            db.execSQL(ManageHashTagTable.SQL_CREATE_TABLE);
//            db.execSQL(ManageCategoryCodeTable.SQL_CREATE_MANAGE_CATEGORY_ENTRIES);
//            db.execSQL(RepeatTransactionsTable.SQL_CREATE_ENTRIES);
//            db.execSQL(ExceptKeywordTable.SQL_CREATE_TABLE);
//            db.execSQL(TransactionsTable.indexing);

        }catch(SQLiteException e){
            LOGE(TAG, e.toString());
        }

    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i2) {

        db.beginTransaction();

//        db.execSQL(OilTable.SQL_DELETE_ENTRIES);
//        db.execSQL(BudgetTable.SQL_DELETE_ENTRIES);
        db.execSQL(CardTable.SQL_DELETE_ENTRIES);
//        db.execSQL(CategoryCodeTable.SQL_DELETE_ENTRIES);
//        db.execSQL(InstallmentTable.SQL_DELETE_ENTRIES);
//        db.execSQL(OilNearByCompanyTable.SQL_DELETE_ENTRIES);
//        db.execSQL(SMSTable.SQL_DELETE_ENTRIES);
//        db.execSQL(TransactionsTable.SQL_DELETE_ENTRIES);
//        db.execSQL(RepeatTransactionsTable.SQL_DELETE_ENTRIES);
//        db.execSQL(UserTable.SQL_DELETE_ENTRIES);
//        db.execSQL(TestTable.SQL_DELETE_ENTRIES);
//        db.execSQL(HashTagTable.SQL_DELETE_ENTRIES);
//        db.execSQL(GoogleCalendarTable.SQL_DELETE_ENTRIES);
//        db.execSQL(ManageHashTagTable.SQL_DELETE_ENTRIES);
//        db.execSQL(ManageCategoryCodeTable.SQL_DELETE_ENTRIES);
//        db.execSQL(ExceptKeywordTable.SQL_DELETE_ENTRIES);
        onCreate(db);
        db.setTransactionSuccessful();
        db.endTransaction();
    }


    @Override
    protected void finalize() throws Throwable {
        this.close();
        super.finalize();
    }

}