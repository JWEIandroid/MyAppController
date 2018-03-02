package ssm.service;

import ssm.model.User;
import ssm.model.goods;

import java.util.List;

public interface GoodsService extends BaseService<goods>{

    List<goods> list();

    void save(goods goods);

    void update(goods goods);

    void delete(int id);

    goods get(int userid);

    goods getbyname(String name);

    goods getgoodsByGoodId(int goodid);

    List<goods> getgoodlikename(String name);

    List<goods> getgoodwithtype(String type);


    List<goods> getgoodsByUserid(int userid);

    List<goods> list_discovery();
    List<goods> list_withoutstatus();









}
