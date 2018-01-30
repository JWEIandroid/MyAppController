package ssm.model;

import java.io.Serializable;

public class salerecord implements Serializable {

    private int id;
    private int userid;
    private int goodsid;
    private String date;
    private int user_sale_id;
    private int shouhuomsg;
    private User user;
    private shuohuomsg shuohuomsg_data;


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public shuohuomsg getShuohuomsg_data() {
        return shuohuomsg_data;
    }

    public void setShuohuomsg_data(shuohuomsg shuohuomsg_data) {
        this.shuohuomsg_data = shuohuomsg_data;
    }

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
