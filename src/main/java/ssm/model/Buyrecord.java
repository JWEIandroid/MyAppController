package ssm.model;

import java.io.Serializable;
import java.sql.Date;

public class Buyrecord implements Serializable {

    private int id;
    private int userid;
    private int goodsid;
    private int shouhuomsgid;
    private String date;
    private User user;
    private shouhuomsg shouhuomsg;
    private goods goods;


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ssm.model.shouhuomsg getShouhuomsg() {
        return shouhuomsg;
    }

    public void setShouhuomsg(ssm.model.shouhuomsg shouhuomsg) {
        this.shouhuomsg = shouhuomsg;
    }

    public ssm.model.goods getGoods() {
        return goods;
    }

    public void setGoods(ssm.model.goods goods) {
        this.goods = goods;
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
