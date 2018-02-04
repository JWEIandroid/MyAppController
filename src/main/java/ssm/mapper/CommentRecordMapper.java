package ssm.mapper;

import ssm.model.Comment;

import java.util.List;

public interface CommentRecordMapper  extends BaseMapper<Comment>{

   List<Comment> select(Comment comment);
   void save(Comment comment);
   void delete(Comment comment);


}
