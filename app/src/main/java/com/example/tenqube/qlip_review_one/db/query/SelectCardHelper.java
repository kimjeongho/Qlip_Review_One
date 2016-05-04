package com.example.tenqube.qlip_review_one.db.query;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;


import com.example.tenqube.qlip_review_one.constans.Constants;
import com.example.tenqube.qlip_review_one.data.CardTableData;
import com.example.tenqube.qlip_review_one.db.table.CardTable;

import java.util.ArrayList;

import static com.example.tenqube.qlip_review_one.util.LogUtil.LOGE;
import static com.example.tenqube.qlip_review_one.util.LogUtil.makeLogTag;


public class SelectCardHelper extends QueryHelper {
    public static final String TAG= makeLogTag(SelectCardHelper.class);
    public SelectCardHelper(Context context) {
        super(context);
    }

    public ArrayList<CardTableData> loadCardList(){
        ArrayList<CardTableData> cardList=new ArrayList<>();
        String  query=
                SELECT +"*"+
                FROM+ CardTable.TABLE_NAME+
                WHERE+CardTable.COLUMN_EXCEPT_TYPE+"="+ Constants.EXCEPT_NO+
                GROUP_BY+CardTable.COLUMN_CARD_TOTAL_CARD_NAME+","+CardTable.COLUMN_CARD_TOTAL_CARD_TYPE+
                ORDER_BY+CardTable.COLUMN_PRIORITY+ASC;

        Cursor c=null;
        try {
            c = runQuery(query);
            if (c != null) {
                if (c.moveToFirst()) {
                    while (!c.isAfterLast()) {
                        cardList.add(CardTable.populateModel(c));
                        c.moveToNext();
                    }
                }
            }
        } catch (SQLException e) {
            LOGE(TAG, e.toString());
        } finally {
            if (c != null)
                c.close();
        }

        return  cardList;

    }

    public void  insertCard(CardTableData cardTableData){
        insert(CardTable.TABLE_NAME, CardTable.populateContent(cardTableData));
    }

    /*public void updateCard(CardTableData cardTableData){
        ContentValues values =new ContentValues();
        values.put(CardTable.TABLE_NAME,cardTableData.cardName);
        update(CardTable.TABLE_NAME, values, CardTable.COLUMN_CARD_ID + "=?", new String[]{cardTableData.cardId + ""});
    }*/

    public void updateCard(CardTableData cardTableData){
        ContentValues values =new ContentValues();
        values.clear();
        values.put(CardTable.COLUMN_CARD_NAME,cardTableData.cardName);
        values.put(CardTable.COLUMN_CARD_CHANGE_NAME,cardTableData.changeCardName);
        values.put(CardTable.COLUMN_CARD_TOTAL_CARD_NAME,cardTableData.totalCardName);
        update(CardTable.TABLE_NAME, values, CardTable.COLUMN_CARD_ID + "=?", new String[]{" " + cardTableData.cardId});
    }

    /*public void deleteCard(String cardId){
        delete(CardTable.TABLE_NAME,CardTable.COLUMN_CARD_ID+"=?",new String[]{cardId});
    }*/

    public void deleteCard(String cardIds){
//            delete(CardTable.TABLE_NAME,CardTable.COLUMN_CARD_ID ,new String[]{cardIds});
        delete(CardTable.TABLE_NAME,CardTable.COLUMN_CARD_ID+ IN + "(" +cardIds+ ")",null);
//        delete(CardTable.TABLE_NAME,CardTable.COLUMN_CARD_ID+ IN + "( ? )", new String[]{cardIds});
    }



}
