package org.disx;

import io.quarkus.panache.common.Sort;
import io.vertx.core.json.JsonObject;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Transactional
@ApplicationScoped
public class CommentService {

    @Inject
    Producer producer;

    public List<Comment> getComments(){
       return Comment.listAll(Sort.descending("createdAt"));
    }

    public List<Comment> getCommentsByDisxId(Long disxId) {
        return Comment.list("disxId = ?1 order by createdAt desc", disxId);
    }

    public void save(Comment comment) {
        comment.setCreatedAt(LocalDateTime.now());
        Comment.persist(comment);
        producer.send(createMessage(comment.getDisxId(), false));
    }

    public void deleteComment(Long id) {
        Comment comment = Comment.findById(id);
        Comment.deleteById(id);
        producer.send(createMessage(comment.getDisxId(), true));
    }

    private JsonObject createMessage(Long id, Boolean isDeleted) {
        JsonObject message = new JsonObject();
        message.put("disxId", id);
        message.put("isDeleted", isDeleted);
        return message;
    }
}
