package ssm.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ssm.mapper.MessageBoardMapper;
import ssm.model.MessageBoard;
import ssm.service.MessageBoardService;

import java.util.List;

@Service
public class MessageBoardServiceImpl extends BaseServiceImpl<MessageBoard> implements MessageBoardService {


    @Autowired
    MessageBoardMapper messageBoardMapper;

    @Override
    public void save(MessageBoard messageBoard) {
        messageBoardMapper.save(messageBoard);

    }

    public void delete(MessageBoard messageBoard) {
        messageBoardMapper.delete(messageBoard);
    }

    public List<MessageBoard> select(MessageBoard messageBoard) {
        return messageBoardMapper.select(messageBoard);
    }

    public List<Integer> selectWriter(MessageBoard messageBoard) {
        return messageBoardMapper.selectWriter(messageBoard);
    }
}
