package cz.osu.itemrecordsbe.controllers;

import cz.osu.itemrecordsbe.models.AppUser;
import cz.osu.itemrecordsbe.models.Comment;
import cz.osu.itemrecordsbe.models.Item;
import cz.osu.itemrecordsbe.repositories.AppUserRepository;
import cz.osu.itemrecordsbe.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/comments/")
@CrossOrigin
public class CommentController {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    AppUserRepository userRepository;

    @GetMapping("all")
    ResponseEntity<List<Comment>> getAll() {
        List<Comment> comments = commentRepository.findAll();
        for (Comment comment : comments) {
            comment.setUser(null);
        }
        return ResponseEntity.ok(comments);
    }

    @GetMapping("all/{userId}")
    ResponseEntity<List<Comment>> getAllByUser(@PathVariable("userId") Long userId) {
        AppUser retUser = userRepository.findByUserId(userId);
        List<Comment> ret = new ArrayList<>();
        List<Comment> comments = commentRepository.findAll();
        for (Comment comment: comments) {
            comment.getUser().setComments(null);
            comment.getUser().setItems(null);
            comment.getUser().setInterestGroups(null);
            if (comment.getUser().getUserId().equals(retUser.getUserId()))
                ret.add(comment);
        }
        return ResponseEntity.ok(ret);
    }

    @PostMapping(value = "add/{userId}")
    ResponseEntity<String> addItem(@RequestBody Comment comment, @PathVariable("userId") Long userId) {
        // if is empty
        /*
        if (item.isItemEmpty(item))
            return new ResponseEntity<>("Item is null", HttpStatus.BAD_REQUEST);
         */
        Long lastId;
        if(commentRepository.findAll().isEmpty())
            lastId = 1L;
        else {
            lastId = commentRepository.findTopByOrderByCommentIdDesc().getCommentId();
            lastId++;
        }
        comment.setCommentId(lastId);
        AppUser user = userRepository.findByUserId(userId);
        comment.setUser(user);
        commentRepository.save(comment);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}
