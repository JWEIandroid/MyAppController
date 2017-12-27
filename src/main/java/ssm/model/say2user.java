package ssm.model;

import java.io.Serializable;

public class say2user implements Serializable {

    private int id;
    private int user_from;
    private  int user_to;
    private  String content;
    private  String type;
    private  String update_time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_from() {
        return user_from;
    }

    public void setUser_from(int user_from) {
        this.user_from = user_from;
    }

    public int getUser_to() {
        return user_to;
    }

    public void setUser_to(int user_to) {
        this.user_to = user_to;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }
}
