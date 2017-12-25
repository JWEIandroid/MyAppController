package ssm.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.JsonView.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;


//@Entity
//@Table(name = "user")

public class User implements Serializable {

    private int id;
    private String name;
    private String tel;
    private String password;
    private String token;
    private String adress;
    private String description;
    private String sex;

    public void setId(int id) {
        this.id = id;
    }

    public User() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSex() {
        return sex;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTel() {
        return tel;
    }

    public String getPassword() {
        return password;
    }

    public String getToken() {
        return token;
    }

    public String getAdress() {
        return adress;
    }

    public String getDescription() {
        return description;
    }


}
