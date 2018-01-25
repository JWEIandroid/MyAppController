package ssm.model;

import java.io.Serializable;

public class salerecord implements Serializable {

    private int id;
    private int userid;
    private int goodsid;
    private String date;
    private int user_sale_id;
    private int shouhuomsg;


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

    public void setGoodsid(int goodid) {
        this.goodsid = goodsid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getUser_sale_id() {
        return user_sale_id;
    }

    public void setUser_sale_id(int user_sale_id) {
        this.user_sale_id = user_sale_id;
    }

    public int getShouhuomsg() {
        return shouhuomsg;
    }

    public void setShouhuomsg(int shouhuomsg) {
        this.shouhuomsg = shouhuomsg;
    }
}
