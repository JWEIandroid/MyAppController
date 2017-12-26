package ssm.mapper;

import org.apache.ibatis.annotations.Param;
import ssm.model.goodimgs;

import java.util.List;

public interface GoodImgMapper extends BaseMapper<goodimgs> {

    void save(goodimgs goodimgs);

    void update(goodimgs goodimgs);

    void delete(int id);

    goodimgs get(int id);

    List<goodimgs> list();

    List<String> getimgs(int id);

}
