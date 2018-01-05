package ssm.service;

import ssm.mapper.BaseMapper;
import ssm.model.goodimgs;

import java.util.List;

public interface GoodsImgService extends BaseService<goodimgs>{

    void save(goodimgs goodimgs);

    void update(goodimgs goodimgs);

    void delete(int id);

    goodimgs get(int id);

    List<String> getImgByGoodid(int goodid);


}
