package cz.osu.itemrecordsbe.services;

import cz.osu.itemrecordsbe.models.Comment;
import org.springframework.http.ResponseEntity;

public interface CommentService {
    ResponseEntity<Object> getAllComments();
    ResponseEntity<Object> getCommentsByUserId(Long userId);
    ResponseEntity<Object> addComment(Comment comment, Long userId);
}
