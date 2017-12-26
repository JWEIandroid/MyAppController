package ssm.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ssm.mapper.GoodImgMapper;
import ssm.model.goodimgs;
import ssm.service.GoodsImgService;

import java.util.List;


@Service
public class GoodImgsServiceImpl extends BaseServiceImpl<goodimgs> implements GoodsImgService {

    @Autowired
    GoodImgMapper goodImgMapper;


    @Override
    public void save(goodimgs goodimgs) {
        goodImgMapper.save(goodimgs);
    }

    @Override
    public void update(goodimgs goodimgs) {
        goodImgMapper.update(goodimgs);
    }

    @Override
    public void delete(int id) {
        goodImgMapper.delete(id);
    }

    @Override
    public goodimgs get(int id) {
        return goodImgMapper.get(id);
    }

    @Override
    public List<goodimgs> list() {
        return goodImgMapper.list();
    }

    public List<String> getimgs(int goodid) {
        List<String> list = goodImgMapper.getimgs(goodid);
        System.out.println("serviceImpl list :");
        System.out.println(list.size());
        return list;
    }

}
