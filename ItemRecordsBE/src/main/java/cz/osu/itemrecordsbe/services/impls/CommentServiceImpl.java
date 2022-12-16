package cz.osu.itemrecordsbe.services.impls;

import cz.osu.itemrecordsbe.models.AppUser;
import cz.osu.itemrecordsbe.models.Comment;
import cz.osu.itemrecordsbe.repositories.CommentRepository;
import cz.osu.itemrecordsbe.services.AppUserService;
import cz.osu.itemrecordsbe.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private AppUserService userService;

    @Override
    public ResponseEntity<Object> getAllComments() {
        List<Comment> ret = commentRepository.findAll();
        ret.forEach(comment -> comment.setUser(null));
        return ResponseEntity.ok(ret);
    }

    @Override
    public ResponseEntity<Object> getCommentsByUserId(Long userId) {
        if (!userService.existsByUserId(userId))
            return ResponseEntity.notFound().build();

        AppUser user = userService.getUserById(userId);
        List<Comment> comments = commentRepository.findAll();

        List<Comment> ret = new ArrayList<>();
        comments.forEach(comment -> {
            comment.getUser().setComments(null);
            comment.getUser().setItems(null);
            comment.getUser().setInterestGroups(null);
            if (comment.getUser().getUserId().equals(user.getUserId()))
                ret.add(comment);
        });

        return ResponseEntity.ok(ret);
    }

    @Override
    public ResponseEntity<Object> addComment(Comment comment, Long userId) {
        if (!userService.existsByUserId(userId))
            return ResponseEntity.notFound().build();

        AppUser user = userService.getUserById(userId);
        comment.setUser(user);
        commentRepository.save(comment);

        return ResponseEntity.ok().build();
    }
}
