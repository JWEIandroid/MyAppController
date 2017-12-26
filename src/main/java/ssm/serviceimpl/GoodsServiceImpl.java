package ssm.serviceimpl;

import ssm.mapper.GoodMapper;
import ssm.model.goods;
import ssm.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class GoodsServiceImpl extends BaseServiceImpl<goods> implements GoodsService {

    @Autowired
    GoodMapper goodMapper;

    public void save(goods goods) {
        goodMapper.save(goods);
    }

    public void update(goods goods) {
        goodMapper.update(goods);
    }

    @Override
    public void delete(int id) {
        goodMapper.delete(id);
    }

    @Override
    public goods get(int id) {
        return goodMapper.get(id);
    }

    @Override
    public List<goods> list() {
        return goodMapper.list();
    }


    public goods getbyname(String name) {
        return goodMapper.getbyname(name);
    }
}
