package com.example.tenqube.qlip_review_one.tranning;

import android.content.Context;

import com.example.tenqube.qlip_review_one.data.CardTableData;
import com.example.tenqube.qlip_review_one.db.query.SelectCardHelper;

import java.util.ArrayList;

/**
 * Created by tenqube on 2016-06-07.
 */
public class TranningSelectCardModel {
    private Context mContext;
    private SelectCardHelper selectCardHelper;

    public TranningSelectCardModel(Context context){
        this.mContext = context;
        selectCardHelper = new SelectCardHelper(context);
    }

    public void insertCardData(CardTableData cardTableData){
        selectCardHelper.insertCard(cardTableData);
    }

    public ArrayList<CardTableData> loadCardList(){
        return selectCardHelper.loadCardList();
    }

    public void updateCard(CardTableData cardTableData){
        selectCardHelper.updateCard(cardTableData);
    }

    public void deleteCard(String cardId){
        selectCardHelper.deleteCard(cardId);
    }

    public boolean checkCardName(ArrayList<CardTableData> cardTableDataArrayList, String cardName, int cardType){
        for (CardTableData item : cardTableDataArrayList){
            if (cardName.equals(item.totalCardName) && cardType == item.totalCardType){
                return true;
            }
        }
        return false;
    }

    public ArrayList<CardTableData> getSelectedCardList(ArrayList<CardTableData> cardList){
        ArrayList<CardTableData> selectedCardList = new ArrayList<>();
        for (CardTableData cardModel : cardList){
            if (cardModel.flag){
                selectedCardList.add(cardModel);
            }
        }
        return selectedCardList;
    }

    public ArrayList<CardTableData> getLongClickCard(ArrayList<CardTableData> cardList){
        ArrayList<CardTableData> selectedCardList = new ArrayList<>();
        for (CardTableData cardModel : cardList){
            if (cardModel.longFlag){
                selectedCardList.add(cardModel);
            }
        }
        return selectedCardList;
    }

    public ArrayList<String> getCheckCardList(ArrayList<CardTableData> cardList){
        ArrayList<String> selectedCardList = new ArrayList<>();
        for (CardTableData cardModel : cardList) {
            if (cardModel.flag){
                selectedCardList.add(""+cardModel.cardId);
            }
        }
        return selectedCardList;
    }

}
