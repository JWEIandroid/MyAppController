package ssm.model;

import java.io.Serializable;
import java.sql.Date;

public class buyrecord implements Serializable {

    private int id;
    private int userid;
    private int goodid;
    private int shuohuomsg;
    private Date date;

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

    public int getShuohuomsg() {
        return shuohuomsg;
    }

    public void setShuohuomsg(int shuohuomsg) {
        this.shuohuomsg = shuohuomsg;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
