package ssm.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ssm.mapper.CommentRecordMapper;
import ssm.model.Comment;
import ssm.model.MessageBoard;
import ssm.service.BaseService;
import ssm.service.CommentRecordService;

import java.util.List;


@Service
public class CommentRecordServiceImpl extends BaseServiceImpl<Comment> implements CommentRecordService {

    @Autowired
    CommentRecordMapper commentRecordMapper;


    @Override
    public void save(Comment comment) {
        commentRecordMapper.save(comment);
    }

    public void delete(Comment comment) {
        commentRecordMapper.delete(comment);
    }

    public List<Comment> select(Comment comment) {
        return commentRecordMapper.select(comment);
    }

    public List<Comment> selectByGoodsId(Comment comment) {
        return commentRecordMapper.selectByGoodsId(comment);
    }

    public void deleteByUseridandDate(Comment comment) {
        commentRecordMapper.deleteByUseridandDate(comment);
    }

    public List<Integer> selectGoodsIdByReceiverId(int receiverid) {
        return commentRecordMapper.selectGoodsIdByReceiverId(receiverid);
    }

    @Override
    public List<Comment> list() {
        return commentRecordMapper.list();
    }

    public List<Comment> QueryByContent(String content) {
        return commentRecordMapper.QueryByContent(content);
    }

    public void deleteWithId(int id) {
        commentRecordMapper.deleteWithId(id);
    }

    @Override
    public void update(Comment comment) {
        commentRecordMapper.update(comment);
    }

    @Override
    public Comment get(int id) {
        return commentRecordMapper.get(id);
    }


}
