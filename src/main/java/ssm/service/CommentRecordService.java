package ssm.service;

import ssm.model.Comment;

import java.util.List;

public interface CommentRecordService extends BaseService<Comment>{

    void save(Comment comment);
    void delete(Comment comment);
    List<Comment> select(Comment comment);


}
