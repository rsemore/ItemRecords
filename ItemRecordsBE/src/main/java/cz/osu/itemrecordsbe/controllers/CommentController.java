package cz.osu.itemrecordsbe.controllers;

import cz.osu.itemrecordsbe.models.Comment;
import cz.osu.itemrecordsbe.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/comments/")
@CrossOrigin
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("all")
    ResponseEntity<Object> getAllComments() {
        return commentService.getAllComments();
    }

    @GetMapping("all/{userId}")
    ResponseEntity<Object> getAllCommentsByUser(@PathVariable("userId") Long userId) {
        return commentService.getCommentsByUserId(userId);
    }

    @PostMapping(value = "add/{userId}")
    ResponseEntity<Object> addComment(@Valid @RequestBody Comment comment, @PathVariable("userId") Long userId) {
        return commentService.addComment(comment, userId);
    }

}
