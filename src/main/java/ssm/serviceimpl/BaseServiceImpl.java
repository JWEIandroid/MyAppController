package ssm.serviceimpl;

import org.springframework.stereotype.Service;
import ssm.mapper.BaseMapper;
import ssm.service.BaseService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("baseService")
public class BaseServiceImpl<T> implements BaseService<T> {


    BaseMapper<T> baseMapper;

    public void save(T t) {
        baseMapper.save(t);
    }

    public void update(T t) {
        baseMapper.update(t);
    }

    public void delete(int id) {
        baseMapper.delete(id);
    }

    public T get(int id) {
        return baseMapper.get(id);
    }

    public List<T> list() {
        return baseMapper.list();
    }

    public Map<String, Object> successRespMap(Map<String, Object> map, String message, Object data) {
        if (map == null) {
            map = new HashMap<String, Object>();
        }
        map.put("error_code", "0");
        map.put("message", message);
        map.put("data", data);
        return map;
    }

    public Map<String, Object> errorRespMap(Map<String, Object> map, String message) {
        if (map == null) {
            map = new HashMap<String, Object>();
        }
        map.put("error_code", "-1");
        map.put("message", message);
        map.put("data", "");
        return map;
    }





}
