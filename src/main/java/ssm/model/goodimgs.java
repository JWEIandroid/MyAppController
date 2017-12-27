package ssm.model;

import java.io.Serializable;

public class goodimgs implements Serializable {

    private int id;
    private int goodid;
    private String url;


    public void setId(int id) {
        this.id = id;
    }

    public void setGoodid(int goodid) {
        this.goodid = goodid;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public int getGoodid() {
        return goodid;
    }

    public String getUrl() {
        return url;
    }



}
