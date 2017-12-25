package ssm.mapper;

import ssm.model.goods;

import java.util.List;

public interface GoodMapper extends BaseMapper<goods> {

    List<goods> list();

    void delete(int id);

    goods getUser(String name, String password);

    void save(goods goods);

    void update(goods goods);
}

