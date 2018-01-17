package ssm.service;

import org.springframework.stereotype.Service;
import ssm.model.Buyrecord;

import java.util.List;

public interface BuyRecordService extends BaseService<Buyrecord>{



    void save(Buyrecord buyrecord);

    void delete(int id);

    List<Buyrecord> list_user(int userid);

    List<Buyrecord> list();

    Buyrecord selectone(int userid,String date);
}
