package ssm.service;

import ssm.model.MessageBoard;

import java.util.List;


public interface MessageBoardService extends BaseService<MessageBoard> {


    void save(MessageBoard messageBoard);

    void delete(MessageBoard messageBoard);


    List<MessageBoard> select(MessageBoard messageBoard);

    List<Integer> selectWriter(MessageBoard messageBoard);



}
