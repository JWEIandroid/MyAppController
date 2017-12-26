package ssm.mapper;

import ssm.model.goods;

import java.util.List;

public interface GoodMapper extends BaseMapper<goods> {

    List<goods> list();

    void delete(int id);

    void save(goods goods);

    void update(goods goods);

    goods get(int id);

    goods getbyname(String name);
}

