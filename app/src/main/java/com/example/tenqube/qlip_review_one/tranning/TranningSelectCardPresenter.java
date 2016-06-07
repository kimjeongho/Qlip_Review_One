package com.example.tenqube.qlip_review_one.tranning;

import android.widget.Toolbar;

import com.example.tenqube.qlip_review_one.data.CardTableData;

import java.util.ArrayList;

/**
 * Created by tenqube on 2016-06-07.
 */
public interface TranningSelectCardPresenter {
    void settingToolbar(Toolbar toolbar);
    ArrayList<CardTableData> loadCardData();
    void confirm(ArrayList<CardTableData> selectedCardList);
    void deleteConfirm(ArrayList<CardTableData> cardList, String cardName);
    void updateTextConfirm(ArrayList<CardTableData> cardList, String changeCardName, int cardType);
    void onClickCardType(int id);
}
