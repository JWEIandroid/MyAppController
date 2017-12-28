package ssm.mapper;

import ssm.model.imgs;

import java.util.List;

public interface ImgMapper extends BaseMapper<imgs>{

    void save(imgs imgs);

    void update(imgs imgs);

    void delete(int id);

    imgs get(int id);

    List<imgs> list();
}
