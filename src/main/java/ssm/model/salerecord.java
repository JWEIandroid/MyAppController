package ssm.model;

import java.io.Serializable;

public class salerecord implements Serializable {

    private int id;
    private int userid;
    private int goodid;
    private String date;
    private int user_sale_id;
    private int shuohuomsg;


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

    public int getGoodid() {
        return goodid;
    }

    public void setGoodid(int goodid) {
        this.goodid = goodid;
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

    public int getShuohuomsg() {
        return shuohuomsg;
    }

    public void setShuohuomsg(int shuohuomsg) {
        this.shuohuomsg = shuohuomsg;
    }
}
