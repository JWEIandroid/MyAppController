package ssm.service;

import ssm.model.Comment;
import ssm.model.MessageBoard;

import java.util.List;

public interface CommentRecordService extends BaseService<Comment> {

    void save(Comment comment);

    void delete(Comment comment);

    List<Comment> select(Comment comment);

    List<Comment> selectByGoodsId(Comment comment);

    void deleteByUseridandDate(Comment comment);


}
