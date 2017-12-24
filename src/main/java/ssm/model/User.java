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

    public User() {
    }

    private User(Builder builder) {
        id = builder.id;
        name = builder.name;
        tel = builder.tel;
        password = builder.password;
        token = builder.token;
        adress = builder.adress;
        description = builder.description;
        sex = builder.sex;
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

    public static final class Builder {
        private int id;
        private String name;
        private String tel;
        private String password;
        private String token;
        private String adress;
        private String description;
        private String sex;

        public Builder() {
        }

        public Builder id(int val) {
            id = val;
            return this;
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Builder tel(String val) {
            tel = val;
            return this;
        }

        public Builder password(String val) {
            password = val;
            return this;
        }

        public Builder token(String val) {
            token = val;
            return this;
        }

        public Builder adress(String val) {
            adress = val;
            return this;
        }

        public Builder description(String val) {
            description = val;
            return this;
        }

        public Builder sex(String val) {
            sex = val;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
