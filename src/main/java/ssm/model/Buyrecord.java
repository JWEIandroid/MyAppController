package ssm.model;

import java.io.Serializable;
import java.sql.Date;

public class Buyrecord implements Serializable {

    private int id;
    private int userid;
    private int goodsid;
    private int shouhuomsgid;
    private String date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getGoodsid() {
        return goodsid;
    }

    public void setGoodsid(int goodsid) {
        this.goodsid = goodsid;
    }

    public int getShouhuomsgid() {
        return shouhuomsgid;
    }

    public void setShouhuomsgid(int shouhuomsgid) {
        this.shouhuomsgid = shouhuomsgid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
