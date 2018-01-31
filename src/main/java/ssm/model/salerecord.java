package ssm.model;

import java.io.Serializable;

public class salerecord implements Serializable {

    private int id;
    private int userid;
    private int goodsid;
    private String date;
    private int user_sale_id;
    private int shouhuomsgid;
    private goods goods;
    private User user;
    private shouhuomsg shouhuomsg;
    private User user_sale;


    public ssm.model.goods getGoods() {
        return goods;
    }

    public void setGoods(ssm.model.goods goods) {
        this.goods = goods;
    }

    public User getUser_sale() {
        return user_sale;
    }

    public void setUser_sale(User user_sale) {
        this.user_sale = user_sale;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public int getShouhuomsgid() {
        return shouhuomsgid;
    }

    public void setShouhuomsgid(int shouhuomsgid) {
        this.shouhuomsgid = shouhuomsgid;
    }

    public ssm.model.shouhuomsg getShouhuomsg() {
        return shouhuomsg;
    }

    public void setShouhuomsg(ssm.model.shouhuomsg shouhuomsg) {
        this.shouhuomsg = shouhuomsg;
    }
}
