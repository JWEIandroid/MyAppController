package ssm.mapper;

import ssm.model.MessageBoard;

import java.util.List;

public interface MessageBoardMapper extends BaseMapper<MessageBoard>{

  void save(MessageBoard messageBoard);
  void delete(MessageBoard messageBoard);
  List<MessageBoard> select(MessageBoard messageBoard);
  List<Integer> selectWriter(MessageBoard messageBoard);

  List<MessageBoard> list();
  void update(MessageBoard messageBoard);
  List<MessageBoard> selectmsgbycontent(String content);
  void deletebyid(int id);

    MessageBoard get(int id);
}
