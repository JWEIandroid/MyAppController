package ssm.serviceimpl;

import ssm.mapper.GoodMapper;
import ssm.mapper.ImgMapper;
import ssm.model.goods;
import ssm.model.imgs;
import ssm.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ssm.service.ImgsService;

import java.util.List;


@Service
public class ImgServiceImpl extends BaseServiceImpl<imgs> implements ImgsService {

    @Autowired
    ImgMapper imgMapper;

    public void save(imgs img) {
        imgMapper.save(img);
    }

    public void update(imgs img) {
        imgMapper.update(img);
    }

    @Override
    public void delete(int id) {
        imgMapper.delete(id);
    }

    @Override
    public List<imgs> list() {
        return imgMapper.list();
    }

    @Override
    public imgs get(int id) {
        return imgMapper.get(id);
    }
}
