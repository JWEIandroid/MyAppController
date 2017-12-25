package ssm.mapper;

import java.util.List;

public interface BaseMapper<T> {

    public void save(T t);

    public void update(T t) ;

    public void delete(int id) ;

    public T get(int id) ;

    public List<T> list();

}
