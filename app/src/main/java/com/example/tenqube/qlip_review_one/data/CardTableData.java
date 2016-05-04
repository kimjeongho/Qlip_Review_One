package com.example.tenqube.qlip_review_one.data;

import java.io.Serializable;

/**
 * 변경 사항 작성
 * 내용/변경자/날짜
 * ex) line 233 파라미터 추가 /사광진/2015-05-20
 *
 */
public class CardTableData implements Serializable {

    public int cardId;
    public String cardName;
    public String changeCardName;
    public String totalCardName;
    public int exceptType;
    public int cardType;
    public int changeCardType;
    public int totalCardType;
    public boolean flag;
    public boolean longFlag;
    public int billingDate;
    public double totalSum;
    public String memo;
    public String cardImgPath;
    public int priority;
    public String moneyType;

    //    cardName : String,
//    cardType: int,
//    changeCardName : String,
//    changeCardType: int,
//    exceptType,
//    billingDate,(추가 청구기준일),
//    totalSum
//    memo,(추가)


    @Override
    public String toString() {
        return "CardTableData{" +
                "cardId=" + cardId +
                ", cardName='" + cardName + '\'' +
                ", changeCardName='" + changeCardName + '\'' +
                ", totalCardName='" + totalCardName + '\'' +
                ", exceptType=" + exceptType +
                ", cardType=" + cardType +
                ", changeCardType=" + changeCardType +
                ", totalCardType=" + totalCardType +
                ", flag=" + flag +
                ", billingDate=" + billingDate +
                ", totalSum=" + totalSum +
                ", memo='" + memo + '\'' +
                ", cardImgPath='" + cardImgPath + '\'' +
                ", priority=" + priority +
                ", moneyType='" + moneyType + '\'' +
                '}';
    }
}

