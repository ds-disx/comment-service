package org.disx;

import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Transactional
@ApplicationScoped
public class CommentService {

    public List<Comment> getComments(){
       return Comment.listAll(Sort.descending("createdAt"));
    }

    public void save(Comment comment) {
        comment.setCreatedAt(LocalDateTime.now());
        Comment.persist(comment);
    }

    public List<Comment> getCommentsByDisxId(Long disxId) {
        return Comment.list("disxId = ?1 order by createdAt desc", disxId);
    }

    public void deleteComment(Long id) {
        Comment.deleteById(id);
    }
}
