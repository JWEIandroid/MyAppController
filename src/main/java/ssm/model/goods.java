package ssm.model;

import java.io.Serializable;
import java.util.List;

public class goods implements Serializable{

    private int id;
    private String name;
    private String type;
    private float price_before;
    private float price_sale;
    private boolean status;
    private String description;
    private int express;
    private int userid;

    public List<String> getImgurl() {
        return imgurl;
    }

    public void setImgurl(List<String> imgurl) {
        this.imgurl = imgurl;
    }

    private List<String> imgurl;


    public goods() {
    }


    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getPrice_before() {
        return price_before;
    }

    public void setPrice_before(float price_before) {
        this.price_before = price_before;
    }

    public float getPrice_sale() {
        return price_sale;
    }

    public void setPrice_sale(float price_sale) {
        this.price_sale = price_sale;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getExpress() {
        return express;
    }

    public void setExpress(int express) {
        this.express = express;
    }


}
