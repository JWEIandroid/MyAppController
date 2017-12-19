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
public class User implements Serializable{

//    @Min(value = 1)
//    @Id
//    @Column(name = "id")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
//
//    @NotNull
//    @Pattern(regexp = "^[\\u4e00-\\u9fa5]{3,5}$|^[\\dA-Za-z_]{3,10}$")//匹配3-5个汉字，或3-10个字节（中文，英文，数字及下划线(_)）
//    @Column(name = "username", length = 50)
    private String name;

//
//    @NotNull
//    @Pattern(regexp = "^((13[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$")
//    @Column(name = "phone", length = 11 , unique = true)
    private String phone;

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
