package com.example.tenqube.qlip_review_one.db.table;


import android.content.ContentValues;
import android.database.Cursor;
import android.provider.BaseColumns;

import com.example.tenqube.qlip_review_one.constans.Constants;
import com.example.tenqube.qlip_review_one.data.CardTableData;


/**
 * 변경 사항 작성
 * 내용/변경자/날짜
 * ex) line 233 파라미터 추가 /사광진/2015-05-20
 *
 */
public class CardTable extends AbstractTable implements BaseColumns {
    public static final String TABLE_NAME = " CARDS";
    public static final String ALIAS = " cards.";
    public static final String AS_ALIAS = " AS cards ";
    public static final String COLUMN_CARD_ID="card_id_pk";
    public static final String COLUMN_CARD_NAME = "card_name";
    public static final String COLUMN_CARD_CHANGE_NAME = "changed_card_name";
    public static final String COLUMN_CARD_CHANGE_TYPE = "changed_card_type";

    public static final String COLUMN_CARD_TOTAL_CARD_NAME = "total_card_name";
    public static final String COLUMN_CARD_TOTAL_CARD_TYPE = "total_card_type";

    public static final String COLUMN_CARD_TYPE = "card_type";
    public static final String COLUMN_EXCEPT_TYPE = "except_type";
    public static final String COLUMN_BILLING_DATE = "billing_date";
    public static final String COLUMN_TOTAL_SUM = "total_sum";
    public static final String COLUMN_MEMO = "card_memo";
    public static final String COLUMN_CARD_IMG = "card_img";
    public static final String COLUMN_MONEY_TYPE = "money_type";
    public static final String COLUMN_PRIORITY = "priority";


    //    public static final String DEFAULT = "INTEGER not null DEFAULT 0";
    public static final String SQL_CREATE_CARD_TABLE =
                    CREATE_TABLE + TABLE_NAME + " (" +
                            COLUMN_CARD_ID + INTEGER_TYPE+PRIMARY_KEY+AUTOINCREMENT+COMMA_SEP+
                            COLUMN_CARD_NAME + TEXT_TYPE+NOT_NULL+DEFAULT+DEFAULT_TEXT + COMMA_SEP +
                            COLUMN_CARD_CHANGE_NAME + TEXT_TYPE+NOT_NULL+DEFAULT+DEFAULT_TEXT + COMMA_SEP +
                            COLUMN_CARD_TOTAL_CARD_NAME + TEXT_TYPE+NOT_NULL+DEFAULT+DEFAULT_TEXT + COMMA_SEP +

                            COLUMN_EXCEPT_TYPE +INTEGER_TYPE+NOT_NULL+ DEFAULT + Constants.EXCEPT_NO +COMMA_SEP+
                            COLUMN_CARD_CHANGE_TYPE +INTEGER_TYPE+NOT_NULL+ DEFAULT +DEFAULT_INT +COMMA_SEP+
                            COLUMN_CARD_TOTAL_CARD_TYPE +INTEGER_TYPE+NOT_NULL+ DEFAULT +DEFAULT_INT +COMMA_SEP+

                            COLUMN_BILLING_DATE +INTEGER_TYPE+NOT_NULL+ DEFAULT +"1" +COMMA_SEP+
                            COLUMN_TOTAL_SUM +REAL_TYPE+NOT_NULL+ DEFAULT +DEFAULT_INT +COMMA_SEP+
                            COLUMN_MEMO +TEXT_TYPE+NOT_NULL+ DEFAULT +DEFAULT_TEXT +COMMA_SEP+
                            COLUMN_CARD_IMG +TEXT_TYPE+NOT_NULL+ DEFAULT +DEFAULT_TEXT +COMMA_SEP+
                            COLUMN_PRIORITY+INTEGER_TYPE+NOT_NULL+DEFAULT+DEFAULT_INT +COMMA_SEP+
                            COLUMN_MONEY_TYPE +TEXT_TYPE+NOT_NULL+ DEFAULT +DEFAULT_TEXT +COMMA_SEP+
                            COLUMN_CARD_TYPE +INTEGER_TYPE+NOT_NULL+ DEFAULT + DEFAULT_INT +
                            " )";


    public static final String SQL_DELETE_ENTRIES =
                           DROP_TABLE_IF_EXISTS + TABLE_NAME;


    public static CardTableData populateModel(Cursor c) {
        CardTableData model = new CardTableData();
        model.cardId = c.getInt(c.getColumnIndex(COLUMN_CARD_ID));
        model.cardName = c.getString(c.getColumnIndex(COLUMN_CARD_NAME));
        model.changeCardName = c.getString(c.getColumnIndex(COLUMN_CARD_CHANGE_NAME));
        model.totalCardName = c.getString(c.getColumnIndex(COLUMN_CARD_TOTAL_CARD_NAME));
        model.cardType=c.getInt(c.getColumnIndex(COLUMN_CARD_TYPE));
        model.changeCardType=c.getInt(c.getColumnIndex(COLUMN_CARD_CHANGE_TYPE));
        model.totalCardType=c.getInt(c.getColumnIndex(COLUMN_CARD_TOTAL_CARD_TYPE));
        model.exceptType = c.getInt(c.getColumnIndex(COLUMN_EXCEPT_TYPE));
        model.billingDate=c.getInt(c.getColumnIndex(COLUMN_BILLING_DATE));
        model.totalSum=c.getDouble(c.getColumnIndex(COLUMN_TOTAL_SUM));
        model.memo=c.getString(c.getColumnIndex(COLUMN_MEMO));
        model.cardImgPath=c.getString(c.getColumnIndex(COLUMN_CARD_IMG));
        model.priority=c.getInt(c.getColumnIndex(COLUMN_PRIORITY));
        model.moneyType=c.getString(c.getColumnIndex(COLUMN_MONEY_TYPE));
        return model;
    }

    public static ContentValues populateContent(CardTableData model) {

        ContentValues values = new ContentValues();
        values.put(COLUMN_CARD_NAME, model.cardName==null||"".equals(model.cardName)?DEFAULT_TEXT:model.cardName);
        values.put(COLUMN_CARD_CHANGE_NAME, model.changeCardName==null||"".equals(model.changeCardName)?model.cardName:model.changeCardName);
        values.put(COLUMN_CARD_TOTAL_CARD_NAME, model.totalCardName==null||"".equals(model.totalCardName)?model.cardName:model.totalCardName);
        values.put(COLUMN_CARD_TYPE, model.cardType);
        values.put(COLUMN_CARD_CHANGE_TYPE, model.changeCardType);
        values.put(COLUMN_CARD_TOTAL_CARD_TYPE, model.totalCardType);
        values.put(COLUMN_EXCEPT_TYPE, model.exceptType);
        values.put(COLUMN_BILLING_DATE, model.billingDate==0?1:model.billingDate);
        values.put(COLUMN_TOTAL_SUM, model.totalSum);
        values.put(COLUMN_MEMO, model.memo==null||"".equals(model.memo)?"none":model.memo);
        values.put(COLUMN_CARD_IMG, model.cardImgPath==null||"".equals(model.cardImgPath)?"none":model.cardImgPath);
        values.put(COLUMN_PRIORITY, model.priority);
        values.put(COLUMN_MONEY_TYPE,model.moneyType==null||"".equals(model.moneyType)?"none":model.moneyType);
        return values;

    }

}
